package com.example.reservation_local.Présentation.Reservation

import android.content.Context
import com.example.reservation_local.Présentation.SourceDeDonnée.API.ApiClient
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeBD
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeLocale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Modele(context: Context) {

    var _source_locale : SourceDeDonnéeBD = SourceDeDonnéeLocale(context)
    val _source_API = ApiClient()
    fun obtenirReservation() :List<Réservation>{
        return _source_locale.obtenirRéservations()
    }

    fun supprimerReservationParId(id: Int){
        _source_locale.supprimerreservationparid(id)
        CoroutineScope(Dispatchers.IO).launch {
            val response = _source_API.supprimerReservation(id)
        }
    }

    fun obtenirSalles(): List<Salle> {

       return _source_locale.obtenirSalles()

    }





}