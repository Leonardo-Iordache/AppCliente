package com.example.appcliente.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcliente.R
import com.example.appcliente.responses.Logro

class LogrosAdapter(private val listaLogros: ArrayList<Logro>):
    RecyclerView.Adapter<LogrosAdapter.LogrosViewHolder>(){

        class LogrosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val idLogro = itemView.findViewById<TextView>(R.id.idLogrosText)
            val descripicion = itemView.findViewById<TextView>(R.id.descripciónLogrosText)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogrosAdapter.LogrosViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val logroView = inflater.inflate(R.layout.logro_item, parent, false)
        return LogrosViewHolder(logroView)
    }

    override fun onBindViewHolder(holder: LogrosViewHolder, position: Int) {
        val logro: Logro = listaLogros[position]
        val textViewID = holder.idLogro
        val textViewDescripcion = holder.descripicion
        textViewID.text = logro.id.toString()
        textViewDescripcion.text = logro.descripcion.toString()
    }

    override fun getItemCount(): Int {
        return listaLogros.size
    }


}