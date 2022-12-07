package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Paquete(
    @SerializedName("id")
    @Expose
    val idPaquete: Int,

    @SerializedName("direccion")
    @Expose
    val direction: String,

    @SerializedName("estado")
    @Expose
    val estado: Boolean,

    @SerializedName("fecha entrega")
    @Expose
    val fechaEntrega: Date,

    @SerializedName("fecha recogida")
    @Expose
    val fechaRecogida: Date,

    @SerializedName("id buzon")
    @Expose
    val idBuzon: Int,

    @SerializedName("id cliente")
    @Expose
    val idCliente: Int,

    @SerializedName("id repartidor")
    @Expose
    val idRepartidor: Int,
)