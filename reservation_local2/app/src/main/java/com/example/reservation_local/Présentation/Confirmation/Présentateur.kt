// Présentateur.kt
package com.example.reservation_local.Présentation.Confirmation

import android.os.Bundle
import android.util.Log
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class Présentateur(private val vue: Vue) {

    val modèle = Modèle(vue.requireContext())
    private var job: Job? = null
    lateinit var salles: List<Salle>
    var salle: Salle? = null


    fun onViewCreated(bundle: Bundle?) {
        if (bundle != null) {
            val _nomSalle = bundle.getString("nomSalle")
            val _imageSalle = bundle.getString("imageSalle")
            val _nombrePersonnes = bundle.getString("nombrePersonnes")
            val _date = bundle.getString("date") // Récupérer la date
            val _heureArrivee = bundle.getString("heureArrivee") // Récupérer l'heure
            val _heureDepart = bundle.getString("heureDepart") // Récupérer l'heure

            val imageResId = vue.getImage().context.resources.getIdentifier(
                _imageSalle, // Le nom du fichier de l'image, sans l'extension.
                "drawable",       // Le dossier où se trouve l'image, généralement "drawable".
                vue.getImage().context.packageName // Le nom du package de votre application.
            )

            // Vérifiez si l'identifiant de ressource existe avant de le définir.
            if (imageResId != 0) {
                vue.getImage().setImageResource(imageResId)
            } else {
                // Gérer le cas où l'image n'est pas trouvée. Par exemple, définir une image par défaut.
                vue.getImage().setImageResource(R.drawable.salle1)
            }
            vue.setNomSalle(_nomSalle)
            vue.setNombrePersonnes(_nombrePersonnes)
            vue.setDate(_date)
            vue.setHeureArrivee(_heureArrivee)
            vue.setHeureDepart(_heureDepart)
        }
    }

    fun onRetourButtonClicked(bundle: Bundle?){
        vue.navigateToPresentation(bundle)

    }

    fun traiterSalles(){
        job = CoroutineScope(Dispatchers.IO).launch {
            //Log.d("Walidsalles2", "heytraiterSalles")
            salles = modèle.obtenirSalles()
            //Log.d("WalidsallesVerifi", salles.toString())
            if(!salles.isNullOrEmpty()){
                //Log.d("WalidnomSalle", vue.nomSalle.text.toString())
                salle = salles.find { it.nomSalle.equals(vue.nomSalle.text.toString()) }
                //Log.d("Walidsalles", salle.toString())
            }

        }
    }

    fun ajoutReservation(){
        //Log.d("Walidreser2", "heytraiterAjoutReservation")
        CoroutineScope(Dispatchers.IO).launch{

            var id = modèle.obtenirReservation().size+1
            var reservation = salle?.let { Réservation(id, it.id, vue.txtHeureArrivee.text.toString(), vue.txtHeureDepart.text.toString(), vue.txtDate.text.toString()) }
            //Log.d("WalidreserverVerif", reservation.toString())
            if (reservation != null) {
                //Log.d("Walidreservation", "heyReservation")

                modèle.ajoutReservation(reservation)
                salle?.let { modèle.ajoutSalle(it) }
                Log.d("WalidSalleajouter", salle.toString())
                vue.ouvrirCalendrierAvecDetails(vue.nomSalle.text.toString(), vue.nombrePersonnes.text.toString(), vue.txtHeureArrivee.text.toString(), vue.txtHeureDepart.text.toString(), vue.txtDate.text.toString())

            }
        }



    }




}





