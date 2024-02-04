package com.example.reservation_local.Présentation.Accueil

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.example.reservation_local.Présentation.SourceDeDonnée.API.ApiClient
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.Présentation.SourceDeDonnée.SourceDeDonnée
import com.example.reservation_local.Présentation.SourceDeDonnée.SourceDeDonnéeBidon
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeBD
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeLocale
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Modèle (context: Context) {

    var _source_locale : SourceDeDonnéeBD = SourceDeDonnéeLocale(context)
    var _source : SourceDeDonnée = SourceDeDonnéeBidon()

    val _source_API = ApiClient()

    suspend fun obtenirSalles(): List<Salle> {

        return try {
            _source_API.obtenirSalles()
        } catch (e: Exception) {

            e.printStackTrace()
            listOf()
        }

    }

    suspend fun obtenirRéservations(): List<Réservation> {
        return try {
            _source_API.obtenirReservation()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }

    @SuppressLint("ServiceCast")
    fun connexionPerdu(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}
