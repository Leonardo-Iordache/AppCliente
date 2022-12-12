package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Logro(
    @SerializedName("idLogros")
    @Expose
    val idLogros: Int,

    @SerializedName("idClientes")
    @Expose
    val idCliente: Int
)