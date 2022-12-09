package com.example.appcliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.appcliente.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.appcliente.login.LogInActivity
import com.example.appcliente.registro.RegisterActivity
import com.example.appcliente.server.RestAPIService


class InitialScreen : AppCompatActivity() {
    private lateinit var logInButton: Button
    private lateinit var registerButton: Button
    private lateinit var binding: ActivityMainBinding
    private val apiService = RestAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService.getSomething()
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.let {
            logInButton = it.logInButton
            registerButton = it.registerButton
        }

        setContentView(binding.root)

        logInButton.setOnClickListener {
            executeLogInActivity()
        }

        registerButton.setOnClickListener {
            executeRegisterActivity()
        }
    }


    private fun executeLogInActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun executeRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}