package com.example.appcliente.mainscreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcliente.R
//import com.example.appcliente.databinding.MainScreenBinding
import com.example.appcliente.mqtt.MqttClient
import com.example.appcliente.responses.Paquete
import com.example.appcliente.server.RestAPIService
import kotlinx.coroutines.*
import okhttp3.internal.wait
import org.eclipse.paho.client.mqttv3.*
import java.lang.Thread.sleep

class MainScreenActivity : AppCompatActivity() {
    //private lateinit var binding: MainScreenBinding
    private lateinit var logrosButon: Button
    private lateinit var historicoButton: Button
    private val channelID = "clienteID"
    private var mqttClient = MqttClient(this)
    private val apiService = RestAPIService()
    lateinit var paquetes: ArrayList<Paquete>
    private var job:Job = Job()
    private var codigo = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        val defaultCbClient = object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                var buzon = "0"
                if (topic == "buzon/entregas"){
                    buzon = message.toString()
                    Log.d(
                        this.javaClass.name, "Receive message: ${message.toString()} from topic: $topic"
                    )
                }
                else if(topic == "buzon/codigo"){
                    codigo = message.toString()
                    Log.d(
                        this.javaClass.name, "Receive message: $codigo from topic: $topic"
                    )
                }
                Log.d(this.javaClass.name, codigo)
                Log.d(this.javaClass.name, "Mensaje recibido")

                createNotificationChannel()
                createNotification("Tu paquete ha sido entregado, buzon: $buzon", textContent = codigo)
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d(this.javaClass.name, "Connection lost ${cause.toString()}")
                Toast.makeText(applicationContext, "Conexión perdida con MQTT", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d(this.javaClass.name, "Delivery completed")
            }
        }

        logrosButon = findViewById(R.id.botonLogros)
        historicoButton = findViewById(R.id.botonHistorico)
        val recyclerViewPaquete = findViewById<View>(R.id.recycler_viewPaquetes) as RecyclerView
        val user = intent.extras?.getString("usuario")

        if (user != null) {
            runBlocking {
                job = launch {
                    paquetes = apiService.getAllPackages(user.toInt())
                }
            }
            for (i in paquetes){
                Log.d(this.javaClass.name, i.toString())
            }
        }

        job.invokeOnCompletion{
            Log.d(this.javaClass.name, "Paquetes cogidos")

            mqttClient.connect(cbClient = defaultCbClient)

            val adapter = MainScreenAdapter(paquetes)
            recyclerViewPaquete.adapter = adapter
            recyclerViewPaquete.layoutManager = LinearLayoutManager(this)

            logrosButon.setOnClickListener {
                val intent = Intent(this, LogrosActivity::class.java)
                intent.putExtra("idUsuario", user.toString())
                startActivity(intent)
            }

            historicoButton.setOnClickListener {
                val intent = Intent(this, HistoricoActivity::class.java)
                intent.putExtra("idUsuario", user.toString())
                startActivity(intent)
            }
        }
    }


    fun createNotificationChannel() {
        val name = "nuevo paquete"
        val descriptionText = "tu paquete ya ha sido entregado"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(textTitle: String, textContent: String) {
        val notification = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg).setContentTitle(textTitle)
            .setContentText(textContent).setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

        with(NotificationManagerCompat.from(this)) {
            notify(0, notification)
        }
    }
}