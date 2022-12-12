package com.example.appcliente.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcliente.R
//import com.example.appcliente.databinding.ActivityLogrosBinding
import com.example.appcliente.responses.Logro
import com.example.appcliente.server.RestAPIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LogrosActivity : AppCompatActivity() {
    private val apiService = RestAPIService()
    lateinit var logros: ArrayList<Logro>
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)
        val recyclerViewLogro = findViewById<View>(R.id.recycler_viewLogros) as RecyclerView
        val user = intent.extras?.getString("idUsuario")

        if (user != null) {
            runBlocking {
                job = launch {
                    logros = apiService.getLogros(user.toInt())
                }
            }
            for (i in logros){
                Log.d(this.javaClass.name, i.toString())
            }
            Log.d(this.javaClass.name, "logros cogidos del usuario $user")
        }

        job.invokeOnCompletion {
            val adapter = LogrosAdapter(logros)
            recyclerViewLogro.adapter = adapter
            recyclerViewLogro.layoutManager = LinearLayoutManager(this)
        }
    }
}