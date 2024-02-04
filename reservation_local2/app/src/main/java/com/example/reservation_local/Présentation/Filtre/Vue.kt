package com.example.reservation_local.Présentation.Filtre

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reservation_local.Présentation.Presentation.Vue
import com.example.reservation_local.R
import java.util.Calendar
import java.util.Locale


class Vue: Fragment() {
    lateinit var btnFiltre: Button
    lateinit var btnDate: Button
    lateinit var btnHeureArrivee: Button
    lateinit var btnHeureDepart: Button
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btnWin: Button
    lateinit var btnLinux: Button
    lateinit var btn_listes_place: List<Button>
    lateinit var btn_listes_equipement: List<Button>
    var afficherChargement: Boolean = true

     var pr=Présentateur(this)


     var btnindex: Int=0
    var btnindexx: Int=0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_filtre, container, false)
        btnFiltre = view.findViewById(R.id.btnFiltrer)
        btnDate = view.findViewById(R.id.btnDate)
        btnHeureArrivee = view.findViewById(R.id.btnHeureArrivee)
        btnHeureDepart = view.findViewById(R.id.btnHeureDepart)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btnWin = view.findViewById(R.id.btnWin)
        btnLinux = view.findViewById(R.id.btnLinux)
        btn_listes_place = listOf(btn1, btn2, btn3, btn4, btnWin, btnLinux)
        btn_listes_equipement = listOf(btnWin, btnLinux)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFiltre.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("afficherCharger", afficherChargement.toString())
                    putString("date", btnDate.text.toString())
                    putString("heureArrivee", btnHeureArrivee.text.toString())
                    putString("heureDepart", btnHeureDepart.text.toString())
                    putString("equipement", btn_listes_equipement.get(btnindex).text.toString())
                    putString("place", btn_listes_place[btnindexx].text.toString())
                    val selectedValue = when {
                        btn1.isSelected -> 1
                        btn2.isSelected -> 2
                        btn3.isSelected -> 3
                        btn4.isSelected -> 4
                        else -> -1
                    }
                    val messageTextView = view.findViewById<TextView>(R.id.messageTextView)
                    val messageequipTextView = view.findViewById<TextView>(R.id.messageequipementTextView)

                    if (selectedValue == -1) {
                        messageTextView.text = "Vous devez choisir le nombre de places avant de filtrer"
                        messageTextView.visibility = View.VISIBLE


                        return@setOnClickListener
                    }
                    else {
                        messageTextView.text = ""
                        messageTextView.visibility = View.GONE
                        if (!btnWin.isSelected  && !btnLinux.isSelected) {
                            messageequipTextView.text = "Vous devez choisir un équipement avant de filtrer"
                            messageequipTextView.visibility = View.VISIBLE
                            return@setOnClickListener
                        } else {
                            messageequipTextView.text = ""
                            messageequipTextView.visibility = View.GONE

                    }
                   }

                }

            findNavController().navigate(R.id.action_filtre_to_accueil,bundle)
        }

        btnDate.setOnClickListener {

            pr.choisir_date()

        }



        btnHeureArrivee.setOnClickListener {
            pr.choisir_heure_arrivee()

        }


        btnHeureDepart.setOnClickListener {
            pr.choisir_heure_depart()
        }

        btn_listes_place.forEach { button ->
            button.setOnClickListener { selectedButton ->
                btnindexx= btn_listes_place.indexOf(selectedButton)
                btn_listes_place.forEach { btn ->
                    btn.isSelected = btn == selectedButton
                    btn.setBackgroundColor(
                        if (btn.isSelected) resources.getColor(R.color.button_selected, null)
                        else resources.getColor(R.color.button_default, null)
                    )
                }
            }


        }

        btn_listes_equipement.forEach { button ->
            button.setOnClickListener { selectedButton ->
               btnindex= btn_listes_equipement.indexOf(selectedButton)
                btn_listes_equipement.forEach { btn ->
                    btn.isSelected = btn == selectedButton
                    btn.setBackgroundColor(
                        if (btn.isSelected) resources.getColor(R.color.button_selected, null)
                        else resources.getColor(R.color.button_default, null)
                    )
                }
            }
        }
        }
    }

