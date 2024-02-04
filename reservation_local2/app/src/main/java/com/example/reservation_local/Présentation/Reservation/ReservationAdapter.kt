package com.example.reservation_local.Présentation.Reservation

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reservation_local.Présentation.Reservation.Modele
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import com.example.reservation_local.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class ReservationAdapter(val vue: Vue): RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    val modele = Modele(vue.requireContext())
    var reservations: List<Réservation> = modele.obtenirReservation()
    lateinit var salles: List<Salle>
    private var job: Job? = null



    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomSalle: TextView = view.findViewById(R.id.nomSalleTextView)
        // val imageSalle: ImageView = view.findViewById(R.id.imageSalleImageView)
        val nombrePersonnes: TextView = view.findViewById(R.id.nombrePersonnesTextView)
        val date: TextView = view.findViewById(R.id.dateTextView)
        val heureArrivee: TextView = view.findViewById(R.id.heureArriveeTextView)
        val heureDepart: TextView = view.findViewById(R.id.heureDepartTextView)

        val btnSupprimer: Button = view.findViewById(R.id.btnSupprimer)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationAdapter.ReservationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)
        return ReservationViewHolder(itemView)
    }

    fun traiterReservationAffichage(){
        job = CoroutineScope(Dispatchers.IO).launch {
            salles = modele.obtenirSalles()
            //Log.d("WalidSalles", salles.toString())
            vue.enleverChargement()
            withContext(Dispatchers.Main) {
                // Assurez-vous que toutes les modifications de l'UI sont faites ici
                if(reservations.isNullOrEmpty()){
                    vue.afficherAucuneReservation()
                }
                vue.afficherReservation()

            }
        }


    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        val salle = salles.find { it.id == reservation.idSalle }

        salle?.let {
            holder.nomSalle.text = it.nomSalle
            holder.nombrePersonnes.text = it.nombrePersonnes.toString()
        }

        holder.date.text = reservation.dateArrivee
        holder.heureArrivee.text = reservation.heureArrivee
        holder.heureDepart.text = reservation.heureDepart

        holder.btnSupprimer.setOnClickListener {

            AlertDialog.Builder(vue.context).apply {
                setTitle("Confirmation de suppression") // Titre de la boîte de dialogue
                setMessage("Êtes-vous sûr de vouloir supprimer cette réservation ?") // Message de confirmation


                setPositiveButton("Supprimer") { dialog, which ->
                    modele.supprimerReservationParId(reservation.id)
                    vue.afficherAccueil()
                }


                setNegativeButton("Annuler") { dialog, which ->

                    dialog.dismiss()
                }


                show()


            }
             }



    }




    override fun getItemCount() = reservations.size
}
