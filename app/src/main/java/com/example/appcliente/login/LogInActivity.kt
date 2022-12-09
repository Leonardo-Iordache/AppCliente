package com.example.appcliente.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.appcliente.R
import com.example.appcliente.databinding.ActivityLogInBinding
import com.example.appcliente.mainscreen.MainScreenActivity
import com.example.appcliente.responses.Paquete
import com.example.appcliente.responses.UserLogin
import com.example.appcliente.server.RestAPIService


class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val apiService = RestAPIService()
    private lateinit var logInButton: Button
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var paquetes: ArrayList<Paquete>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        logInButton = binding.logInButtonIniciarSesionActivity
        logInButton.setOnClickListener {
            userEmail = binding.userEMailtext.text.toString()
            userPassword = binding.userPassword.text.toString()
            completeLogIn()
        }
        setContentView(binding.root)
    }

    private fun completeLogIn() {
        Log.d(this.javaClass.name, userEmail + userPassword)
        val userLogin = apiService.validateUser(userEmail, userPassword)
        val intent = Intent(this, MainScreenActivity::class.java)
        intent.putExtra("usuario", userLogin.toString())
        startActivity(intent)

        //Toast.makeText(applicationContext, "Credenciales invalidas", Toast.LENGTH_SHORT).show()
    }
}
