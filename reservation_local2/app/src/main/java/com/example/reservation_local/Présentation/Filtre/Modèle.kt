package com.example.reservation_local.Présentation.Filtre

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Modèle {

    var dateSélectionnée: String? = null
    var heureSélectionnée: String? = null
    var placesSélectionnées: List<String> = emptyList()
    var équipementsSélectionnés: List<String> = emptyList()

    suspend fun effectuerUneTâcheAsynchrone() {
    CoroutineScope(Dispatchers.IO).launch {

        dateSélectionnée = "Nouvelle date"
        heureSélectionnée = "Nouvelle heure"
        placesSélectionnées = listOf("Place 1", "Place 2","Place 3","Place 4")
        équipementsSélectionnés = listOf("Windows", "Linux")
    }
}
}