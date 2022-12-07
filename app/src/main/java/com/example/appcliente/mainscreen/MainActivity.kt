package com.example.appcliente.mainscreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.appcliente.R
import com.example.appcliente.mqtt.MqttClient
import org.eclipse.paho.client.mqttv3.*


class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "clienteID"
    private val c = this
    private var mqttClient = MqttClient(c)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val defaultCbClient = object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d(this.javaClass.name, "Receive message: ${message.toString()} from topic: $topic")
                createNotificationChannel()
                createNotification("Tu paquete ha sido entregado", textContent = message.toString())
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d(this.javaClass.name, "Connection lost ${cause.toString()}")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d(this.javaClass.name, "Delivery completed")
            }
        }

        mqttClient.connect(cbClient = defaultCbClient)
    }



    fun createNotificationChannel() {
        val name = "nuevo paquete"
        val descriptionText = "tu paquete ya ha sido entregado!!!!"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(textTitle: String, textContent: String){
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(0, notification)
        }
    }
}