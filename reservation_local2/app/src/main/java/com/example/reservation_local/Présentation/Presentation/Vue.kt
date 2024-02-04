package com.example.reservation_local.Présentation.Presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reservation_local.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

class Vue : Fragment() {

    lateinit var imgSalle: ImageView
    lateinit var btnRetour: ImageView
    lateinit var btnReservation: Button
    lateinit var nomSalle: TextView
    lateinit var nombrePersonnes: TextView
    lateinit var txtDate: TextView
    lateinit var txtHeureArrivee: TextView
    lateinit var txtHeureDepart: TextView
    lateinit var txtEquipement: TextView
    lateinit var présentateur: Présentateur

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_presentation, container, false)
        btnRetour = view.findViewById(R.id.btnRetour)
        nomSalle = view.findViewById(R.id.nomSalle)
        nombrePersonnes = view.findViewById(R.id.nombrePersonnes)
        txtDate = view.findViewById(R.id.txtDate)
        txtHeureArrivee = view.findViewById(R.id.txtHeureArrivee)
        txtHeureDepart = view.findViewById(R.id.txtHeureDepart)
        txtEquipement = view.findViewById(R.id.txtEquipement)
        btnReservation = view.findViewById(R.id.btnReservation)
        imgSalle = view.findViewById(R.id.imgSalle)
        présentateur  = Présentateur(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val _equipement = arguments?.getString("equipement")
        val _nomSalle = arguments?.getString("nomSalle")
        val _imageSalle = arguments?.getString("imageSalle")
        val _nombrePersonnes = arguments?.getString("nombrePersonnes")
        val _date = arguments?.getString("date")
        val _heureArrivee = arguments?.getString("heureArrivee")
        val _heureDepart = arguments?.getString("heureDepart")

        txtDate.text = _date
        txtHeureArrivee.text = _heureArrivee
        txtHeureDepart.text = _heureDepart
        txtEquipement.text = _equipement

        // Utilisez ces informations pour mettre à jour les vues
        nomSalle.text = _nomSalle
        val imageResId = imgSalle.context.resources.getIdentifier(
            _imageSalle, // Le nom du fichier de l'image, sans l'extension.
            "drawable",       // Le dossier où se trouve l'image, généralement "drawable".
            imgSalle.context.packageName // Le nom du package de votre application.
        )

        // Vérifiez si l'identifiant de ressource existe avant de le définir.
        if (imageResId != 0) {
            imgSalle.setImageResource(imageResId)
        } else {
            // Gérer le cas où l'image n'est pas trouvée. Par exemple, définir une image par défaut.
            imgSalle.setImageResource(R.drawable.salle1)
        }
        nombrePersonnes.text = _nombrePersonnes

        val bundle = Bundle().apply {
            putString("nomSalle", _nomSalle)
            if (_imageSalle != null) {
                putString("imageSalle", _imageSalle)
            }
            putString("nombrePersonnes", _nombrePersonnes.toString())
            // Ajoutez d'autres détails au besoin
        }



        btnRetour.setOnClickListener {
            findNavController().navigate(R.id.action_presentation_to_accueil)
        }

        btnReservation.setOnClickListener {

            val bundle = Bundle().apply {
                putString("nomSalle", _nomSalle)
                putString("imageSalle", _imageSalle)
                putString("nombrePersonnes", _nombrePersonnes.toString())
                putString("date", txtDate.text.toString()) // Ajoutez la date
                putString("heureArrivee", txtHeureArrivee.text.toString())
                putString("heureDepart", txtHeureDepart.text.toString())// Ajoutez l'heure
                // Ajoutez d'autres détails au besoin
            }
            findNavController().navigate(R.id.action_presentation_to_confirmation, bundle)
        }

    }


}