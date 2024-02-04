package com.example.reservation_local.Présentation.Accueil

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Présentateur(val vue: Vue) {


    lateinit var salles: List<Salle>
    lateinit var réservation: List<Réservation>
    val modèle = Modèle(vue.requireContext())
    private var job: Job? = null



    fun charger_salles_filtrées() {
        if(!modèle.connexionPerdu(vue.requireContext())){
            traiterConnexionPerdu(vue.requireContext())
        } else {

        job = CoroutineScope(Dispatchers.IO).launch {
            if(vue.afficherCharger){
                vue.afficherChargement()
                vue.afficherCharger = false
            }
            réservation = modèle.obtenirRéservations()
            if(verifier_filtre()){
            salles = modèle.obtenirSalles().filter { salle ->
                salle.nombrePersonnes.toString() == vue._place &&
                        salle.equipement == vue._equipement &&
                        !estRéservée(salle.id, vue._date, vue._heureArrivee, vue._heureDepart)

            }
                if(!vue.afficherCharger){
                    vue.enleverChargement()
                }

            } else{
                salles = emptyList()

            }

            withContext(Dispatchers.Main) {

                    if(salles.isEmpty()){
                        vue.afficherMessageAucuneSalleDisponible()
                    } else {
                        vue.afficherSalles(salles)
                    }


            }
        }
         }
    }

    private fun estRéservée(idSalle: Int, date: String, heureArrivée: String, heureDépart: String): Boolean {

        return réservation.any { réservation ->
            réservation.idSalle == idSalle &&
                    réservation.dateArrivee == date &&
                    réservation.heureArrivee < heureDépart &&
                    réservation.heureDepart > heureArrivée
        }
    }

    /*
    fun chargerSalles() {
        job = CoroutineScope(Dispatchers.Main).launch {
            salles = modèle.obtenirSalles()
            if(salles != null){
                vue.afficherSalles(salles)
            } else {
                salles?.let { vue.afficherSalles(it) }
            }
        }
    }
     */

    fun traiterNavigationVersFiltre() {
        vue.naviguerVersFiltre()
    }


    fun traiterNavigationVersReservation(){
        vue.naviguerVersReservation()
    }

    fun traiterSelectionSalle(salle: Salle) {
        vue.naviguerVersPresentation(salle)
    }

    fun verifier_filtre(): Boolean {

        if(vue._date != "null" && vue._heureArrivee != "null" && vue._heureDepart != "null" && vue._place != "null" && vue._equipement != "null"){
            return true
        }
        return false
    }

    fun traiterConnexionPerdu(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Aucune connexion internet")
            .setMessage("Regarder votre connexion internet et réessayer.")
            .setNegativeButton("Terminer") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }




}