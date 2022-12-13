package com.example.appcliente.server

import com.example.appcliente.responses.*
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
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/ValidarCliente")
    suspend fun validateUser(
        @Query("usuario") emailCliente: String = "usuarioCliente3",
        @Query("contraseña") password: String = "contraseñaCliente3"
    ): Response<UserLogin>

    //metodo para coger todos los paquetes de un cliente
    @GET("ServidorUbicua-0.0.1-SNAPSHOT/PaquetesCliente")
    suspend fun getPackages(@Query("id") idCliente: Int): Response<ArrayList<Paquete>>

    @GET("ServidorUbicua-0.0.1-SNAPSHOT/LogrosClientes")
    suspend fun getLogros(@Query("id") idCliente: Int): Response<ArrayList<Logro>>
}