package com.example.appcliente.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appcliente.R
import com.example.appcliente.responses.Paquete

class MainScreenAdapter(private val listaPaquetes: ArrayList<Paquete>) :
    RecyclerView.Adapter<MainScreenAdapter.MainScreenViewHolder>() {

    class MainScreenViewHolder(itemView: View): ViewHolder(itemView) {
        val idPaquete = itemView.findViewById<TextView>(R.id.package_text)
        val estado = itemView.findViewById<TextView>(R.id.estadoPaquete)
        val buzon = itemView.findViewById<TextView>(R.id.buzonPaquete)
        val imagen = itemView.findViewById<ImageView>(R.id.package_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenAdapter.MainScreenViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val packageView = inflater.inflate(R.layout.paquete_item, parent, false)
        return MainScreenViewHolder(packageView)
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        val paquete: Paquete = listaPaquetes[position]
        val textViewID = holder.idPaquete
        val textViewEstado = holder.estado
        val textViewBuzon = holder.buzon
        val imageView = holder.imagen
        textViewID.text = paquete.idPaquete.toString()
        textViewBuzon.text = paquete.idBuzon.toString()

        if(textViewEstado.text.toString() == "1"){
            textViewEstado.text = "Entregado"
        }
        else{
            textViewEstado.text = "En reparto"
        }
    }

    override fun getItemCount(): Int {
        return listaPaquetes.size
    }
}