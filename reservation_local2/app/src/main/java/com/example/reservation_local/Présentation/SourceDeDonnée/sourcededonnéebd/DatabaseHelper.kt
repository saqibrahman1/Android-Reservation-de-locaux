package com.example.reservation_local.Présentation.SourceDeDonnée

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "reservation_database.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_SALLES = "Salles"
        private const val TABLE_RESERVATIONS = "Reservations"

        private const val CREATE_TABLE_SALLES = """
            CREATE TABLE $TABLE_SALLES (
                idSalle INTEGER PRIMARY KEY,
                nombrePersonnes INTEGER,
                nomSalle TEXT,
                equipement TEXT,
                heureOuverture TEXT,
                heureFermeture TEXT,
                imageSalle TEXT
            )
        """

        private const val CREATE_TABLE_RESERVATIONS = """
            CREATE TABLE $TABLE_RESERVATIONS (
                idRéservation INTEGER PRIMARY KEY,
                idSalle INTEGER,
                heure_arrivé TEXT,
                heure_départ TEXT,
                date_arrivé TEXT,
                FOREIGN KEY (idSalle) REFERENCES $TABLE_SALLES(idSalle)
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SALLES)
        db.execSQL(CREATE_TABLE_RESERVATIONS)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Ici, vous gérez les mises à jour de votre base de données
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RESERVATIONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SALLES")
        onCreate(db)
    }

    // Suite de la classe DatabaseHelper

    fun ajouter_salle(salle: Salle) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put("idSalle", salle.id)
        values.put("nombrePersonnes", salle.nombrePersonnes)
        values.put("nomSalle", salle.nomSalle)
        values.put("equipement", salle.equipement)
        values.put("heureOuverture", salle.heureOuverture)
        values.put("heureFermeture", salle.heureFermeture)
        values.put("imageSalle", salle.imageSalle)

        db.insert(TABLE_SALLES, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun obtenir_salles(): List<Salle> {
        val salles = mutableListOf<Salle>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_SALLES"

        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val salle = Salle(
                    cursor.getInt(cursor.getColumnIndex("idSalle")),
                    cursor.getInt(cursor.getColumnIndex("nombrePersonnes")),
                    cursor.getString(cursor.getColumnIndex("nomSalle")),
                    cursor.getString(cursor.getColumnIndex("equipement")),
                    cursor.getString(cursor.getColumnIndex("heureOuverture")),
                    cursor.getString(cursor.getColumnIndex("heureFermeture")),
                    cursor.getString(cursor.getColumnIndex("imageSalle"))
                )
                salles.add(salle)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return salles
    }

    fun modifier_salle(salle: Salle): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put("nombrePersonnes", salle.nombrePersonnes)
        values.put("nomSalle", salle.nomSalle)
        values.put("equipement", salle.equipement)
        values.put("heureOuverture", salle.heureOuverture)
        values.put("heureFermeture", salle.heureFermeture)
        values.put("imageSalle", salle.imageSalle)

        // Mise à jour de la ligne
        val success = db.update(TABLE_SALLES, values, "idSalle = ?", arrayOf(salle.id.toString()))
        db.close()
        return success
    }

    fun supprimer_salle(idSalle: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_SALLES, "idSalle = ?", arrayOf(idSalle.toString()))
        db.close()
    }

// Suite de la classe DatabaseHelper

    fun ajouter_reservation(reservation: Réservation) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("idRéservation", reservation.id)
        values.put("idSalle", reservation.idSalle)
        values.put("heure_arrivé", reservation.heureArrivee)
        values.put("heure_départ", reservation.heureDepart)
        values.put("date_arrivé", reservation.dateArrivee)

        db.insert(TABLE_RESERVATIONS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun obtenir_reservations(): List<Réservation> {
        val reservations = mutableListOf<Réservation>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_RESERVATIONS"

        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val reservation = Réservation(
                    cursor.getInt(cursor.getColumnIndex("idRéservation")),
                    cursor.getInt(cursor.getColumnIndex("idSalle")),
                    cursor.getString(cursor.getColumnIndex("heure_arrivé")),
                    cursor.getString(cursor.getColumnIndex("heure_départ")),
                    cursor.getString(cursor.getColumnIndex("date_arrivé"))
                )
                reservations.add(reservation)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return reservations
    }

    fun modifier_reservation(reservation: Réservation): Int {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("idSalle", reservation.idSalle)
        values.put("heure_arrivé", reservation.heureArrivee)
        values.put("heure_départ", reservation.heureDepart)
        values.put("date_arrivé", reservation.dateArrivee)

        // Updating row
        val success = db.update(TABLE_RESERVATIONS, values, "idRéservation = ?", arrayOf(reservation.id.toString()))
        db.close()
        return success
    }

    fun supprimer_reservation(idReservation: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_RESERVATIONS, "idRéservation = ?", arrayOf(idReservation.toString()))
        db.close()
    }

/*
    private fun préRemplirSalles(db: SQLiteDatabase) {
        val sallesInitiales = listOf(
            Salle(1, 4, "Salle A102","Windows", "08:00", "21:00", "salle2"),
            Salle(2, 2, "Salle B103","Linux", "08:00", "21:00", "salle1"),
            Salle(3, 1, "Salle A202","Linux", "08:00", "21:00", "salle1"),
            Salle(4, 1, "Salle A202","Linux", "08:00", "21:00", "salle1")
            // ... ajoutez d'autres salles initiales ici
        )

        sallesInitiales.forEach { salle ->
            val values = ContentValues()
            values.put("idSalle", salle.idSalle)
            values.put("nombrePersonnes", salle.nombrePersonnes)
            values.put("nomSalle", salle.nomSalle)
            values.put("equipement", salle.equipement)
            values.put("heureOuverture", salle.heureOuverture)
            values.put("heureFermeture", salle.heureFermeture)
            values.put("imageSalle", salle.imageSalle)

            db.insert(TABLE_SALLES, null, values)
        }
    }

    private fun préRemplirReservations(db: SQLiteDatabase) {
        val reservationsInitiales = listOf(
            Réservation(1, 1, "12:00","13:00", "2023-11-20"),
            Réservation(2, 1, "15:00","16:00", "2023-11-20"),
            Réservation(3, 2, "08:00","09:00", "2023-11-22"),
            Réservation(4, 2, "10:00","11:00", "2023-11-22"),
            // ... ajoutez d'autres réservations initiales ici
        )

        reservationsInitiales.forEach { reservation ->
            val values = ContentValues()
            values.put("idRéservation", reservation.idRéservation)
            values.put("idSalle", reservation.idSalle)
            values.put("heure_arrivé", reservation.heure_arrivé)
            values.put("heure_départ", reservation.heure_départ)
            values.put("date_arrivé", reservation.date_arrivé)

            db.insert(TABLE_RESERVATIONS, null, values)
        }
    }

 */

}
