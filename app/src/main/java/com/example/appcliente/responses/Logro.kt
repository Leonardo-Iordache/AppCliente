package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Logro(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("descripcion")
    @Expose
    val descripcion: String,

    @SerializedName("estado")
    @Expose
    val estado: Boolean
)