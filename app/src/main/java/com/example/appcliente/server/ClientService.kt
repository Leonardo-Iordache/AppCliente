package com.example.appcliente.server

import com.example.appcliente.responses.Paquete
import com.example.appcliente.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ClientService {
    //metodo de prueba para ver si funciona la conexion
    @POST("ServidorUbicua-0.0.1-SNAPSHOT/EstadoBuzon")
    suspend fun getSomething(): Response<String>

    //metodo para crear un nuevo usuario, usado en la pantalla de registro
    @POST("ServidorUbicua-0.0.1-SNAPSHOT/EstadoBuzon/")
    suspend fun createNewUser(@Body requestBody: UserResponse): Response<UserResponse>

    //metodo para validar un login
    @POST("ServidorUbicua-0.0.1-SNAPSHOT/ValidarUsuario")
    suspend fun validateUser(@Query("id") idCliente: Int, @Query("contraseña") password: String): Response<Paquete>

    //metodo para coger todos los paquetes de un cliente
    @POST("ServidorUbicua-0.0.1-SNAPSHOT/Paquetes")
    suspend fun getPackages(@Query("id") idRepartidor: Int): Response<ArrayList<Paquete>>
}