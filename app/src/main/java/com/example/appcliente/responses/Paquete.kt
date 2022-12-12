package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Paquete(
    @SerializedName("idPaquete")
    @Expose
    val idPaquete: Int,

    @SerializedName("direccion")
    @Expose
    val direction: String,

    @SerializedName("estado")
    @Expose
    val estado: Int,

    @SerializedName("descuento")
    @Expose
    val descuento: Int,

    @SerializedName("idBuzon")
    @Expose
    val idBuzon: Int,

    @SerializedName("idCliente")
    @Expose
    val idCliente: Int,

    @SerializedName("idRepartidor")
    @Expose
    val idRepartidor: Int,

    @SerializedName("fechaEntrega")
    @Expose
    val fechaEntrega: String,

    @SerializedName("fechaRecogida")
    @Expose
    val fechaRecogida: String
){
    override fun toString(): String {
        return "Paquete(idPaquete=$idPaquete, direction='$direction', estado=$estado, descuento=$descuento, idBuzon=$idBuzon, idCliente=$idCliente, idRepartidor=$idRepartidor, fechaEntrega='$fechaEntrega', fechaRecogida='$fechaRecogida')"
    }
}