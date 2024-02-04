package com.example.reservation_local.Présentation.SourceDeDonnée

data class Salle(
    val id: Int,
    val nombrePersonnes: Int,
    val nomSalle: String,
    val equipement: String,
    val heureOuverture: String,
    val heureFermeture: String,
    val imageSalle: String // Assurez-vous que cela représente un chemin ou une URI si c'est une chaîne
)
