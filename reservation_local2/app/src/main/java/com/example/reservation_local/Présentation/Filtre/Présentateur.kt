package com.example.reservation_local.Présentation.Filtre

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.view.View
import android.widget.TextView
import com.example.reservation_local.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale


class Présentateur(val vue: Vue) {




    fun choisir_date() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)



        val datePickerDialog = DatePickerDialog(vue.requireContext(), DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

            val selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
            vue.btnDate.text = selectedDate



        }, year, month, day)

        datePickerDialog.show()
    }

    fun choisir_heure_arrivee() {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)


        val timePickerDialog = TimePickerDialog(vue.requireContext(), { _, selectedHour, selectedMinute ->

            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            vue.btnHeureArrivee.text = formattedTime
        }, hour, minute, true)

        timePickerDialog.show()

    }

    fun choisir_heure_depart() {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)


        val timePickerDialog = TimePickerDialog(vue.requireContext(), { _, selectedHour, selectedMinute ->

            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            vue.btnHeureDepart.text = formattedTime
        }, hour, minute, true)

        timePickerDialog.show()

    }
}
