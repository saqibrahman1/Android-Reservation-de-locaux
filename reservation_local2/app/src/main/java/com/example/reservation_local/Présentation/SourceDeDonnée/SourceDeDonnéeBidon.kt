package com.example.reservation_local.Présentation.SourceDeDonnée

class SourceDeDonnéeBidon: SourceDeDonnée {
    override fun obtenirSalles(): List<Salle> {
        // Retourner une liste de salles codées en dur pour le développement
        return listOf(
            Salle(1, 4, "Salle A102","Windows", "08:00", "21:00", "salle2"),
            Salle(2, 2, "Salle B103","Linux", "08:00", "21:00", "salle1"),
            Salle(3, 1, "Salle A202","Linux", "08:00", "21:00", "salle1"),
            Salle(4, 3, "Salle B205","Windows", "08:00", "21:00", "salle2")
            ,
            // ... ajoutez d'autres salles selon vos besoins
        )
    }

    override fun obtenirRéservation(): List<Réservation> {
        // Retourner une liste de salles codées en dur pour le développement
        return listOf(
            Réservation(1, 1, "12:00","13:00", "2023-11-20"),
            Réservation(2, 1, "15:00","16:00", "2023-11-20"),
            Réservation(3, 2, "08:00","09:00", "2023-11-22"),
            Réservation(4, 2, "10:00","11:00", "2023-11-22"),
            // ... ajoutez d'autres salles selon vos besoins
        )
    }
}