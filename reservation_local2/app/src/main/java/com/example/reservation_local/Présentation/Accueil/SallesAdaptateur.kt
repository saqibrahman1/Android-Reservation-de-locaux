package com.example.reservation_local.Présentation.Accueil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.R



class SallesAdaptateur(private val salles: List<Salle>, private val onRoomClick: (Salle) -> Unit) : RecyclerView.Adapter<SallesAdaptateur.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgSalle: ImageView = view.findViewById(R.id.imgSalle)
        val nomSalle: TextView = view.findViewById(R.id.nomSalle)
        val nombrePersonnes: TextView = view.findViewById(R.id.nombrePersonnes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.liste_salles, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val salles = salles[position]

        holder.nomSalle.text = salles.nomSalle
        holder.nombrePersonnes.text = salles.nombrePersonnes.toString()

        val imageResId = holder.itemView.context.resources.getIdentifier(
            salles.imageSalle,
            "drawable",
            holder.itemView.context.packageName
        )


        if (imageResId != 0) {
            holder.imgSalle.setImageResource(imageResId)
        } else {

            holder.imgSalle.setImageResource(R.drawable.salle1)
        }

        holder.itemView.setOnClickListener {
            onRoomClick(salles)
        }

    }






    override fun getItemCount() = salles.size
}