package com.example.appcliente.server

import android.service.autofill.UserData
import android.util.Log
import com.example.appcliente.responses.Logros
import com.example.appcliente.responses.Paquete
import com.example.appcliente.responses.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.util.*
import kotlin.collections.ArrayList
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Headers.Companion.toHeaders
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RestAPIService {
    private val serverURL = "http://192.168.225.129:8080/"

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
    fun addUser(
        id: Int,
        usuario: String,
        nombre: String,
        contrasena: String,
        apellidos: String,
        dni: String,
        direccion: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
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
    }

    fun validateUser(userEmail: String, userPassword: String): Int{
        var id = 0
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.validateUser(userEmail, userPassword)
            if (call.isSuccessful) {
                val items = call.body()
                id = items!!.id
                Log.d(this.javaClass.name, id.toString())
            }
        }
        return id
    }

    //funcion de prueba para un GET
    fun getSomething() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getSomething()

            if (call.isSuccessful) {
                val response = call.body()
                Log.d(this.javaClass.name, call.toString())
                Log.d(this.javaClass.name, response.toString())
            } else {
                Log.d(this.javaClass.name, "Error en POST: $serverURL")
            }
        }
    }

    fun getAllPackages(id: Int): ArrayList<Paquete> {
        val paqueteTemporal: ArrayList<Paquete> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getPackages(id)
            val response = call.body()
            if (call.isSuccessful) {
                response?.let {
                    for (i in it) {
                        paqueteTemporal.add(i)
                    }
                }
            } else {
                Log.d(this.javaClass.name, "Error en getAllPackages: $serverURL")
            }
        }
        return paqueteTemporal
    }


    fun getLogros(id: Int): ArrayList<Logros> {
        val logrosDesbloqueados: ArrayList<Logros> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = ServiceBuilder.buildService(ClientService::class.java)
            val call = retrofit.getLogros(id)
            if (call.isSuccessful) {
                val response = call.body()
                response?.let {
                    for (i in it) {
                        logrosDesbloqueados.add(i)
                    }
                }
            } else {
                Log.d(this.javaClass.name, "Error en getLogros: $serverURL")
            }
        }
        return logrosDesbloqueados
    }


}