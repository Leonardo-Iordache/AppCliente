package com.example.appcliente.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.appcliente.databinding.ActivityRegisterBinding
import com.example.appcliente.R
import com.example.appcliente.login.LogInActivity
import com.example.appcliente.server.RestAPIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var returnButton: Button
    private lateinit var registerButton: Button
    private lateinit var nameInput: String
    private lateinit var lastNameInput: String
    private lateinit var dniInput: String
    private lateinit var id: String
    private lateinit var passwordInput: String
    private lateinit var confirmPasswordInput: String
    private lateinit var direccionInput: String
    private lateinit var emailInput: String
    private val apiService = RestAPIService()
    private var job: Job = Job()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.let {
            returnButton = it.returnButton
            registerButton = it.registerButton
        }

        returnButton.setOnClickListener {
            finish()
        }

        registerButton.setOnClickListener {
            binding.let {
                nameInput = it.nameText.text.toString().trim()
                lastNameInput = it.lastNameText.text.toString().trim()
                dniInput = it.dniText.text.toString().trim()
                id = it.idText.text.toString().trim()
                passwordInput = it.password.text.toString().trim()
                confirmPasswordInput = it.confirmPassword.text.toString().trim()
                direccionInput = it.direccionText.text.toString().trim()
                emailInput = it.correoText.text.toString().trim()
            }
            completeRegistration()
        }
        setContentView(binding.root)
    }


    private fun completeRegistration() = runBlocking {
        if (passwordInput == confirmPasswordInput) {
            job = launch {
                val userID = apiService.addUser(
                    id.toInt(),
                    emailInput,
                    nameInput,
                    passwordInput,
                    lastNameInput,
                    dniInput,
                    direccionInput
                )
            }
            job.join()
            val intent = Intent(context, LogInActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                applicationContext, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT
            ).show()
        }
    }
}
