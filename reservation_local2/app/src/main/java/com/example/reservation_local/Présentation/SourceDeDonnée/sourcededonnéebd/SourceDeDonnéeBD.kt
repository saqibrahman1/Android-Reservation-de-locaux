package com.example.reservation_local.Présentation.SourceDeDonnée.sourcededonnéebd

import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle

interface SourceDeDonnéeBD {
    fun obtenirSalles(): List<Salle>
    fun obtenirRéservations(): List<Réservation>

    fun ajouterreservation(r:Réservation)

    fun supprimerreservationparid(id: Int)

    fun ajoutersalle(s:Salle)

}