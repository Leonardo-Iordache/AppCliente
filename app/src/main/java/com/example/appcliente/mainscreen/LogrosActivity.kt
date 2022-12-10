package com.example.appcliente.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.appcliente.R
import com.example.appcliente.databinding.ActivityLogrosBinding
import com.example.appcliente.server.RestAPIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LogrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogrosBinding
    private val apiService = RestAPIService()

    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)
        binding = ActivityLogrosBinding.inflate(layoutInflater)
        val user = intent.extras?.getString("usuario")

        if (user != null) {
            runBlocking {
                job = launch {
                    apiService.getLogros(user.toInt())
                }
            }
        }



        setContentView(binding.root)
    }
}