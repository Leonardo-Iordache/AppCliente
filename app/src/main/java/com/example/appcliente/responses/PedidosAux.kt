package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PedidosAux(
    @SerializedName("direccion")
    @Expose
    val direccion: String,

    @SerializedName("pedidosAcumulados")
    @Expose
    val pedidosAcumulados: Int,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("usuario")
    @Expose
    val usuario: String,

    @SerializedName("nombre")
    @Expose
    val nombre: String,

    @SerializedName("apellidos")
    @Expose
    val apellidos: String,

    @SerializedName("contrasena")
    @Expose
    val contrasena: String,

    @SerializedName("dni")
    @Expose
    val dni: String
)
