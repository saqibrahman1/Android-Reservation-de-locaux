package com.example.reservation_local.Présentation.Presentation

import android.content.Context
import android.os.Bundle
import com.example.reservation_local.Présentation.SourceDeDonnée.API.ApiClient
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.Présentation.SourceDeDonnée.SourceDeDonnée
import com.example.reservation_local.Présentation.SourceDeDonnée.SourceDeDonnéeBidon
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeBD
import com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd.SourceDeDonnéeLocale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Modele(context: Context) {
    var _source_locale : SourceDeDonnéeBD = SourceDeDonnéeLocale(context)
    var _source : SourceDeDonnée = SourceDeDonnéeBidon()

    val _source_API = ApiClient()

    suspend fun obtenirSalles(): List<Salle> {

        return try {
            _source_API.obtenirSalles() // apiService est votre instance Retrofit de l'API
        } catch (e: Exception) {
            // Gérer l'exception et retourner une liste vide ou gérer autrement
            e.printStackTrace() // Ceci imprimera l'erreur dans Logcat
            listOf()
        }


        //return _source_locale.obtenirSalles()
    }

    fun obtenirRéservations(): List<Réservation> {
        return _source_locale.obtenirRéservations()
    }
    }


