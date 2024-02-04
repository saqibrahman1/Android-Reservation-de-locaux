package com.example.reservation_local.Présentation.Accueil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.R



class Vue : Fragment() {

    lateinit var _date: String
    lateinit var _heureArrivee: String
    lateinit var _heureDepart: String
    lateinit var _equipement: String
    lateinit var _place: String


    lateinit var btnFiltre: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var sallesAdaptateur: SallesAdaptateur
    private lateinit var présentateur: Présentateur
    private lateinit var btnVoirReservation: ImageButton
    private lateinit var txtAucuneSalleDisponible: TextView
    lateinit var chargement: ProgressBar
    var afficherCharger: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_accueil, container, false)

        chargement = view.findViewById(R.id.chargement)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afficherCharger = arguments?.getString("afficherCharger").toBoolean()
        _date = arguments?.getString("date").toString()
        _heureArrivee = arguments?.getString("heureArrivee").toString()
        _heureDepart = arguments?.getString("heureDepart").toString()
        _equipement = arguments?.getString("equipement").toString()
        _place = arguments?.getString("place").toString()

        Log.d("badg1", _date)
        Log.d("badgHA2", _heureArrivee)
        Log.d("badgHD3", _heureDepart)
        Log.d("badgE4", _equipement)
        Log.d("badgP5", _place)


        recyclerView = view.findViewById(R.id.desSalles)
        recyclerView.layoutManager = LinearLayoutManager(context)
        btnFiltre = view.findViewById(R.id.btnFiltre)
        présentateur = Présentateur(this)
        btnVoirReservation = view.findViewById(R.id.btnVoirReservation)
        txtAucuneSalleDisponible = view.findViewById(R.id.txtAucuneSalleDisponible)




        présentateur.charger_salles_filtrées()



        btnFiltre.setOnClickListener {

            présentateur.traiterNavigationVersFiltre()
        }

        btnVoirReservation.setOnClickListener {
            présentateur.traiterNavigationVersReservation()
        }

    }

    fun afficherSalles(salles: List<Salle>) {
        if(txtAucuneSalleDisponible.visibility == 0) {
            txtAucuneSalleDisponible.visibility = View.GONE
        }
        sallesAdaptateur = SallesAdaptateur(salles) { salle ->
            naviguerVersPresentation(salle)
        }
        recyclerView.adapter = sallesAdaptateur
    }

    fun afficherMessageAucuneSalleDisponible(){
        txtAucuneSalleDisponible.visibility = View.VISIBLE
    }




    fun naviguerVersPresentation(salle: Salle) {
        val bundle = Bundle().apply {
            putString("nomSalle", salle.nomSalle)
            putString("imageSalle", salle.imageSalle)
            putString("nombrePersonnes", salle.nombrePersonnes.toString())
            putString("date", _date)
            putString("heureArrivee", _heureArrivee)
            putString("heureDepart", _heureDepart)
            putString("equipement", _equipement)
        }
        findNavController().navigate(R.id.action_accueil_to_presentation, bundle)
    }


    fun naviguerVersFiltre() {
        findNavController().navigate(R.id.action_accueil_to_filtre)
    }

    fun naviguerVersReservation(){
        findNavController().navigate(R.id.action_accueilFragment_to_reservationFragment)
    }

    fun afficherSallesApresReservations(){
        présentateur.charger_salles_filtrées()
    }


    fun afficherChargement(){
        chargement.visibility = View.VISIBLE
    }

    fun enleverChargement(){
        chargement.visibility = View.INVISIBLE
    }




}