package com.example.appcliente.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("usuario")
    @Expose
    val usuario: String,

    @SerializedName("nombre")
    @Expose
    val nombre: String,

    @SerializedName("contrasena")
    @Expose
    val contrasena: String,

    @SerializedName("apellidos")
    @Expose
    val apellidos: String,

    @SerializedName("dni")
    @Expose
    val dni: String,

    @SerializedName("direccion")
    @Expose
    val direccion: String
){
    override fun toString(): String {
        return "UserResponse(id=$id, usuario='$usuario', nombre='$nombre', contrasena='$contrasena', apellidos='$apellidos', dni='$dni', direccion='$direccion')"
    }
}


