package com.example.appcliente.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appcliente.R
import com.example.appcliente.databinding.ActivityLogInBinding
import com.example.appcliente.mainscreen.MainActivity
import com.example.appcliente.mqtt.MqttClient
import com.example.appcliente.responses.Paquete
import com.example.appcliente.server.RestAPIService
import kotlin.properties.Delegates

class LogInActivity : AppCompatActivity() {
    private lateinit var logInButton: Button
    private lateinit var binding: ActivityLogInBinding
    private var mqttClient = MqttClient(this)
    private val apiService = RestAPIService()
   // var userID by Delegates.notNull<Int>()
    private lateinit var userPassword: String
    private lateinit var paquetes: ArrayList<Paquete>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        binding = ActivityLogInBinding.inflate(layoutInflater)

        binding.let {
            logInButton = it.logInButtonIniciarSesionActivity
            //userID = it.userID.toString()
            userPassword = it.userPassword.toString()
        }
        logInButton.setOnClickListener {
            //searchUserByID()
            completeLogIn()
            /*if (mqttClient.isConnected()) {
                    completeLogIn()
                    //searchUserByID(serverURL)
                }*/
        }
        setContentView(binding.root)
    }

    private fun completeLogIn() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
