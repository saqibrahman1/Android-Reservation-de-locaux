package com.example.reservation_local.Présentation.SourceDeDonnée

interface SourceDeDonnée {
    fun obtenirSalles(): List<Salle>
    fun obtenirRéservation(): List<Réservation>
}