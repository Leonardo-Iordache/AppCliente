package com.example.appcliente.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.appcliente.databinding.ActivityRegisterBinding
import com.example.appcliente.R
import com.example.appcliente.responses.UserResponse
import com.example.appcliente.login.LogInActivity
import com.example.appcliente.server.RestAPIService
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var returnButton: Button
    private lateinit var registerButton: Button

    private lateinit var nameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var dniInput: EditText
    private lateinit var id: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText

    private val apiService = RestAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.let {
            returnButton = it.returnButton
            registerButton = it.registerButton
            nameInput = it.nameText
            lastNameInput = it.lastNameText
            dniInput = it.dniText
            id = it.idText
            passwordInput = it.password
            confirmPasswordInput = it.confirmPassword
        }

        returnButton.setOnClickListener {
            finish()
        }

        registerButton.setOnClickListener {
            completeRegistration()
        }

        setContentView(binding.root)
    }


    private fun completeRegistration() {
        if (passwordInput.text.toString() == confirmPasswordInput.text.toString()) {
            val userResponse = UserResponse(
                id.text.toString(),
                passwordInput.text.toString(),
                nameInput.text.toString(),
                lastNameInput.text.toString(),
                dniInput.text.toString()
            )

            apiService.getSomething()

            /*apiService.addUser(userResponse){
                if(it?.id == null){
                    Log.d(this.javaClass.name, "Error al registrar el usuario")
                }
                else{
                    Log.d(this.javaClass.name, "Registrado correctamente")
                }
            }*/
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}