package com.example.appcliente.server

import com.example.appcliente.responses.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ClientService {
    //metodo de prueba para ver si funciona la conexion
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/BuzonPaquete")
    suspend fun getSomething(): Response<Mailbox>

    //metodo para crear un nuevo usuario, usado en la pantalla de registro
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/NuevoCliente")
    suspend fun createNewUser(
        @Query("id") idCliente: Int,
        @Query("usuario") usuario: String,
        @Query("nombre") nombre: String,
        @Query("contrasena") contrasena: String,
        @Query("apellidos") apellidos: String,
        @Query("dni") dni: String,
        @Query("direccion") direccion: String
    ): Response<Int>

    //metodo para validar un login
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/ValidarUsuario")
    suspend fun validateUser(
        @Query("id") emailCliente: String,
        @Query("contraseña") password: String
    ): Response<UserLogin>

    //metodo para coger todos los paquetes de un cliente
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/Paquetes")
    suspend fun getPackages(@Query("id") idRepartidor: Int): Response<ArrayList<Paquete>>

    @POST("ServidorUbicua-0.0.1-SNAPSHOT/LogrosClientes")
    suspend fun getLogros(@Query("id") idCliente: Int): Response<ArrayList<Logros>>
}