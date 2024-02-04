package com.example.reservation_local.Présentation.Confirmation

import android.content.Context
import com.example.reservation_local.Présentation.SourceDeDonnée.API.ApiClient
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeBD
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeLocale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Modèle(context: Context) {
    var _source_locale : SourceDeDonnéeBD = SourceDeDonnéeLocale(context)
    val _source_API = ApiClient()

    suspend fun obtenirReservation() :List<Réservation>{
        return try {
            _source_API.obtenirReservation()
        } catch (e: Exception){
            e.printStackTrace()
            listOf()
        }
    }

    suspend fun obtenirSalles(): List<Salle> {

        return try {
            _source_API.obtenirSalles()
        } catch (e: Exception) {

            e.printStackTrace()
            listOf()
        }

    }

    fun ajoutReservation(r: Réservation){
            _source_locale.ajouterreservation(r)
            CoroutineScope(Dispatchers.IO).launch{
                val response: Response<Réservation> = _source_API.ajouterReservation(r)
                
            }
    }

    fun ajoutSalle(s: Salle){
        _source_locale.ajoutersalle(s)
    }

}
