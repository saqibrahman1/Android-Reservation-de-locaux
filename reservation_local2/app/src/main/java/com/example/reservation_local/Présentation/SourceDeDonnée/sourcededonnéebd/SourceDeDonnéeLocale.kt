package com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd

import android.content.Context
import com.example.reservation_local.Présentation.SourceDeDonnée.DatabaseHelper
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle

class SourceDeDonnéeLocale(val context: Context): SourceDeDonnéeBD {
    private val dbHelper = DatabaseHelper(context)


    override fun obtenirSalles(): List<Salle> {

        var salles = dbHelper.obtenir_salles()

        dbHelper.close()

        return salles
    }

    override fun obtenirRéservations(): List<Réservation> {
        var reservations = dbHelper.obtenir_reservations()

        dbHelper.close()

        return reservations
    }

    override fun ajouterreservation(r: Réservation) {
        dbHelper.ajouter_reservation(r)

        dbHelper.close()


    }

    override fun supprimerreservationparid(id: Int) {
        dbHelper.supprimer_reservation(id)
    }

    override fun ajoutersalle(s: Salle){
        dbHelper.ajouter_salle(s)
    }


}