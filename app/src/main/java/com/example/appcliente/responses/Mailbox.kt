package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Mailbox(
    @SerializedName("idBuzon")
    @Expose
    val idBuzon: Int,

    @SerializedName("estado")
    @Expose
    val estado: Boolean,

    @SerializedName("codigo")
    @Expose
    val codigo: Int
)
