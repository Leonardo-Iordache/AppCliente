package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("ID")
    @Expose
    var id: String,

    @SerializedName("contraseña")
    @Expose
    var password: String,

    @SerializedName("nombre")
    @Expose
    var name: String,

    @SerializedName("apellido")
    @Expose
    var lastName: String,

    @SerializedName("DNI")
    @Expose
    var dni: String,
)