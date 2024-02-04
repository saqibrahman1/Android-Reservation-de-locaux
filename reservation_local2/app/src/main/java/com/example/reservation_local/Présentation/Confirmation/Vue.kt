
package com.example.reservation_local.Présentation.Confirmation

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reservation_local.R
import java.text.SimpleDateFormat
import java.util.Locale

class Vue : Fragment() {

    lateinit var btnRetour: ImageView
    lateinit var btnConfirmer: Button
    lateinit var txtDate: TextView
    lateinit var txtHeureArrivee: TextView
    lateinit var txtHeureDepart: TextView
    lateinit var nomSalle: TextView
    lateinit var nombrePersonnes: TextView
    lateinit var imgSalle: ImageView

    private lateinit var presenter: Présentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)

        btnRetour = view.findViewById(R.id.btnRetour)
        btnConfirmer = view.findViewById(R.id.btnConfirmer)
        txtDate = view.findViewById(R.id.txtDate)
        txtHeureArrivee = view.findViewById(R.id.txtHeureArrivee)
        txtHeureDepart = view.findViewById(R.id.txtHeureDepart)
        nomSalle = view.findViewById(R.id.nomSalle)
        nombrePersonnes = view.findViewById(R.id.nombrePersonnes)
        imgSalle = view.findViewById(R.id.imgSalle)

        presenter = Présentateur(this)

        val bundle = arguments
        presenter.onViewCreated(bundle)

        btnRetour.setOnClickListener {
            presenter.onRetourButtonClicked(bundle)
        }

        btnConfirmer.setOnClickListener {
            presenter.ajoutReservation()
            navigateToAccueil()
        }

        presenter.traiterSalles()



        return view
    }


    //ok
    fun setNomSalle(nom: String?) {
        nomSalle.text = nom
    }

    fun setImageSalle(imageResource: Int) {
        imgSalle.setImageResource(imageResource)
    }

    fun setNombrePersonnes(nombre: String?) {
        nombrePersonnes.text = nombre
    }

    fun setDate(date: String?) {
        txtDate.text = date
    }

    fun setHeureArrivee(heure: String?) {
        txtHeureArrivee.text = heure
    }
    fun setHeureDepart(heure: String?) {
        txtHeureDepart.text = heure
    }

    fun getImage(): ImageView {
        return imgSalle
    }

    fun navigateToPresentation(bundle: Bundle?) {
        findNavController().navigate(R.id.action_confirmation_to_presentation, bundle)
    }

    fun navigateToAccueil() {
        findNavController().navigate(R.id.action_confirmation_to_accueil)
    }

    fun ouvrirCalendrierAvecDetails(nomSalle: String?, nombrePersonnes: String?, heureArrivee: String?, heureDepart: String?, date: String?) {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val startMillis = format.parse("$date $heureArrivee")?.time ?: return

        val endMillis = format.parse("$date $heureDepart")?.time ?: return

        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            putExtra(CalendarContract.Events.TITLE, "Réservation de $nomSalle")
            putExtra(CalendarContract.Events.DESCRIPTION, "Réservation pour $nombrePersonnes personnes")
        }

        startActivity(intent)
    }



}

