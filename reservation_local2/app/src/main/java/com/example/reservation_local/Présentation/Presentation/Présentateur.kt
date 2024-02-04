package com.example.reservation_local.Présentation.Presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


class Présentateur (val vue: Vue) {

    lateinit var salles: List<Salle>
    var réservation: List<Réservation>
    val modèle = Modele(vue.requireContext())
    private var job: Job? = null

    init {
        réservation = modèle.obtenirRéservations() // Charger les réservations
    }


    suspend fun verifier_date_heure() {

        job = CoroutineScope(Dispatchers.IO).launch {
            var s : Salle? = null
            salles = modèle.obtenirSalles()

            for(salle in salles){
                if(salle.nomSalle == vue.nomSalle.toString()){
                    s = salle
                    break
                }
            }

            if(s !=null)
                !estRéservée(s.id, vue.txtDate.toString(), vue.txtHeureArrivee.toString(), vue.txtHeureDepart.toString())

            } /*
            mettre un else et dans le else, dire que le local est réserver, (boite de dialogue ici)
                        */

            withContext(Dispatchers.Main) {



            }
    }


    fun choisir_date() {
        val calendar = java.util.Calendar.getInstance()
        val year = calendar.get(java.util.Calendar.YEAR)
        val month = calendar.get(java.util.Calendar.MONTH)
        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)

        val datePickerDialog: DatePickerDialog

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            datePickerDialog = DatePickerDialog(vue.requireContext(), android.R.style.Theme_DeviceDefault_Dialog, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
                vue.txtDate.text = selectedDate
            }, year, month, day)
        } else {
            datePickerDialog = DatePickerDialog(vue.requireContext(), DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
                vue.txtDate.text = selectedDate
            }, year, month, day)
        }

        datePickerDialog.show()
    }

    // Reste du code...



    fun choisir_heure_arrivee() {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(vue.requireContext(), { _, selectedHour, selectedMinute ->
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            vue.txtHeureArrivee.text = formattedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    fun choisir_heure_depart() {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(vue.requireContext(), { _, selectedHour, selectedMinute ->
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            vue.txtHeureDepart.text = formattedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun estRéservée(idSalle: Int, date: String, heureArrivée: String, heureDépart: String): Boolean {
        // Logique pour déterminer si une salle est réservée en fonction des critères de filtrage

        return réservation.any { réservation ->
            réservation.idSalle == idSalle &&
                    réservation.dateArrivee == date &&
                    réservation.heureArrivee < heureDépart &&
                    réservation.heureDepart > heureArrivée
        }
    }
}


