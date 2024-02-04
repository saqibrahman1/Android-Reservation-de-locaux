package com.example.reservation_local.Présentation.Reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reservation_local.R

class Vue : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReservationAdapter
    lateinit var btnRetour: ImageView
    lateinit var chargement: ProgressBar
    lateinit var txtAucuneReservation: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mes_reservations, container, false)
        btnRetour = view.findViewById(R.id.btnRetour)
        chargement = view.findViewById(R.id.chargement)
        txtAucuneReservation = view.findViewById(R.id.txtAucuneReservation)

        recyclerView = view.findViewById(R.id.reservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = ReservationAdapter(this)
        adapter.traiterReservationAffichage()

        btnRetour.setOnClickListener {
            findNavController().navigate(R.id.action_reservationFragment_to_accueilFragment)
        }

        return view
    }




    fun afficherReservation(){
        recyclerView.adapter = adapter
    }

    fun afficherAucuneReservation(){
        txtAucuneReservation.visibility = View.VISIBLE
    }

    fun afficherAccueil(){
        findNavController().navigate(R.id.action_reservationFragment_to_accueilFragment)
    }

    fun enleverChargement(){
        chargement.visibility = View.INVISIBLE
    }


}




