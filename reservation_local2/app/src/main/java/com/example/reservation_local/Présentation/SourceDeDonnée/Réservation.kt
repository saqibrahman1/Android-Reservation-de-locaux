package com.example.reservation_local.Présentation.SourceDeDonnée

data class Réservation (
    val id: Int,
    val idSalle: Int,
    val heureArrivee: String,
    val heureDepart: String,
    val dateArrivee: String,
)