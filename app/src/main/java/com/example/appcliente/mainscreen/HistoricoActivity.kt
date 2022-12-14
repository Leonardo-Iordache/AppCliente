package com.example.appcliente.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcliente.R
import com.example.appcliente.databinding.ActivityHistoricoBinding
import com.example.appcliente.server.RestAPIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HistoricoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricoBinding
    private val apiService = RestAPIService()
    private lateinit var totalPedidos: String
    private lateinit var pedidosRecogidos: String
    private lateinit var pedidosEntregados: String
    private lateinit var pedidosEnReparto: String
    private lateinit var totalLogros: String
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        binding.let{
            totalPedidos = it.totalPedidosTextView.text.toString()
            pedidosRecogidos = it.pedidosRecogidosTextView.text.toString()
            pedidosEntregados = it.pedidosEntregadosTextView.text.toString()
            pedidosEnReparto = it.pedidosEnRepartoTextView.text.toString()
            totalLogros = it.totalLogrosTextView.text.toString()
        }
        cogerDatos(binding)
        setContentView(binding.root)
    }


    private fun cogerDatos(binding: ActivityHistoricoBinding){
        val user = intent.extras?.getString("idUsuario")
        runBlocking {
            job = launch {
                if (user != null){
                    totalLogros = apiService.getLogros(user.toInt()).size.toString()
                    pedidosEntregados = apiService.getPaquetesEntregados(user.toInt()).toString()
                    totalPedidos = apiService.getPedidosAcumulados(user.toInt()).toString()
                    pedidosRecogidos = apiService.getPaquetesRecogidos(user.toInt()).toString()
                    pedidosEnReparto = apiService.getPaquetesReparto(user.toInt()).toString()
                }
            }
        }
        job.invokeOnCompletion {
            binding.totalPedidosTextView.text = "Total pedidos: " + totalPedidos
            binding.totalLogrosTextView.text = "Total logros " + totalLogros
            binding.pedidosEntregadosTextView.text = "Pedidos Entregados " + pedidosEntregados
            binding.pedidosEnRepartoTextView.text = "Pedidos en reparto " + pedidosEnReparto
            binding.pedidosRecogidosTextView.text = "Pedidos recogidos " + pedidosRecogidos

        }
    }

}