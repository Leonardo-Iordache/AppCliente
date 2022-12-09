package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Logros(
    @SerializedName("id_logros")
    @Expose
    val idLogros: Int,

    @SerializedName("id_cliente")
    @Expose
    val idCliente: Int
)