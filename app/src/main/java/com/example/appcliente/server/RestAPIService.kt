package com.example.appcliente.server


import android.util.Log
import com.example.appcliente.responses.Logro
import com.example.appcliente.responses.Paquete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class RestAPIService {
    private val serverURL = "http://192.168.1.15:8080/"

    /*fun searchUserByID(retrofit: Retrofit, serverURL: String, userResponse: UserResponse): UserResponse {
        CoroutineScope(Dispatchers.IO).launch {
            val id: Int
            val call =
                retrofit.create(ClientService::class.java).getUserByID()
            val user = call.body()

            if (call.isSuccessful) {
                id = user?.id?.toInt() ?:
                val contrasena = (user?.password ?: String) as String
                val name = (user?.name ?: String) as String
                val lastName = (user?.lastName ?: String) as String
                val dni = (user?.dni ?: String) as String

            } else {
                Log.d(this.javaClass.name, "Error al conectar con el servidor:${serverURL}")
            }
            return UserResponse(id, contrasena)
        }
    }*/

    //añade un nuevo usuario a los
    suspend fun addUser(
        id: Int,
        usuario: String,
        nombre: String,
        contrasena: String,
        apellidos: String,
        dni: String,
        direccion: String
    ) {
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call =
            retrofit.createNewUser(id, usuario, nombre, contrasena, apellidos, dni, direccion)
        val response = call.body()
        Log.d(this.javaClass.name, "AddUser: $response")
        if (response == 0) {
            Log.d(this.javaClass.name, "Correcto")
        } else {
            Log.d(this.javaClass.name, "Error en POST:${serverURL} $call")
        }
    }

    suspend fun validateUser(userEmail: String, userPassword: String): Int {
        var id = 0
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.validateUser(userEmail, userPassword)
        if (call.isSuccessful) {
            val items = call.body()
            id = items!!.id
            Log.d(this.javaClass.name, "El id nuevo es: $id")
        }
        return id
    }



    suspend fun getAllPackages(id: Int): ArrayList<Paquete> {
        val paqueteTemporal: ArrayList<Paquete> = ArrayList()
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.getPackages(id)
        if (call.isSuccessful) {
            val response = call.body()

            response?.let {
                for (i in it) {
                    Log.d(this.javaClass.name, i.toString())
                    paqueteTemporal.add(i)
                }
            }
        } else {
            Log.d(this.javaClass.name, "Error en getAllPackages: $serverURL")
        }
        return paqueteTemporal
    }


    suspend fun getLogros(id: Int): ArrayList<Logro> {
        val logrosDesbloqueados: ArrayList<Logro> = ArrayList()
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getLogros(id)
            if (call.isSuccessful) {
                val response = call.body()
                response?.let {
                    for (i in it) {
                        Log.d(this.javaClass.name, i.toString())
                        logrosDesbloqueados.add(i)
                    }
                }
            } else {
                Log.d(this.javaClass.name, "Error en getLogros: $serverURL")
            }
        return logrosDesbloqueados
    }

    suspend fun getPaquetesEntregados(idCliente: Int): Int{
        var paquetes = 0
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.getPaquetesEntregados(idCliente)
        if(call.isSuccessful){
            val response = call.body()
            if (response != null) {
                paquetes = response.size
            }
        }
        return paquetes
    }

    suspend fun getPaquetesRecogidos(idCliente: Int): Int{
        var paquetes = 0
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.getPaquetesRecogidos(idCliente)
        if(call.isSuccessful){
            val response = call.body()
            if (response != null) {
                paquetes = response.size
            }
        }
        return paquetes
    }

    suspend fun getPaquetesReparto(idCliente: Int): Int{
        var paquetes = 0
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.getPaquetesReparto(idCliente)
        if(call.isSuccessful){
            val response = call.body()
            if (response != null) {
                paquetes = response.size
            }
        }
        return paquetes
    }

    suspend fun getPedidosAcumulados(idCliente: Int): Int{
        var paquetes = 0
        val retrofit = ServiceBuilder.buildService(ClientService::class.java)
        val call = retrofit.getPedidosAcumulados(idCliente)
        if(call.isSuccessful){
            val response = call.body()
            if (response != null) {
                paquetes = response.pedidosAcumulados
            }
        }
        return paquetes
    }
}