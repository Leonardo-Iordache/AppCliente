package com.example.appcliente.server

import android.util.Log
import com.example.appcliente.responses.Paquete
import com.example.appcliente.responses.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestAPIService {
    private val serverURL = "http://192.168.1.129:8080/"

    /*private fun searchUserByID(retrofit: Retrofit, serverURL: String, userResponse: UserResponse): UserResponse {
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
    fun addUser(userData: UserResponse, onResult: (UserResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.createNewUser(userData)
            val response = call.body()

            if (call.isSuccessful) {
                Log.d(this.javaClass.name, "Correcto")
                //onResult(response)
            } else {
                Log.d(this.javaClass.name, "Error en POST:${serverURL}")
            }
        }
    }

    //funcion de prueba para un GET
    fun getSomething(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getSomething()
            val response = call.body()

            if(call.isSuccessful){
                Log.d(this.javaClass.name, call.toString())
                Log.d(this.javaClass.name, response.toString())
            }
            else{
                Log.d(this.javaClass.name, "Error en POST: $serverURL")
            }
        }
    }

    fun getAllPackages(id: Int): ArrayList<Paquete>{
        val paqueteTemporal: ArrayList<Paquete> = ArrayList()

        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getPackages(id)
            val response = call.body()
            paqueteTemporal.add(Paquete(1, 1, "coso"))

            if(call.isSuccessful){
                //TODO: coger los datos del array json y meterlos en una clase -> guardarlos en un arraylist
                //cast from string to arrayjson
            }
            else{
                Log.d(this.javaClass.name, "Error en getAllPackages: $serverURL")
            }
        }
        return paqueteTemporal
    }
}