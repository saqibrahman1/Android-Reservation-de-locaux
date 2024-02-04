package com.example.reservation_local


import android.content.Context
import com.example.reservation_local.Présentation.Confirmation.ConfirmationModel
import com.example.reservation_local.Présentation.Confirmation.SourceDonneConfirmation

import com.example.reservation_local.Présentation.Filtre.Modèle
import com.example.reservation_local.Présentation.Filtre.Présentateur
import com.example.reservation_local.Présentation.Filtre.Vue

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var contextMock: Context

    @Mock
    private lateinit var dbHelperMock: DatabaseHelper

    private lateinit var modelDeConfirmation: ConfirmationModel

    @Before
    fun initialiser() {
        modelDeConfirmation = ConfirmationModel(contextMock)
        modelDeConfirmation.dbHandler = dbHelperMock
    }





class ModèleFiltreTest {

    @Mock
    lateinit var mockVue: Vue

    annotation class Mock

    lateinit var présentateur: Présentateur



    @Test
    fun `Étant donné un objet Modèle,lorsque nous attribuons une date sélectionnée,on obtient la date sélectionnée attendue`()  {
        val modèle = Modèle()
        modèle.dateSélectionnée = "2023-11-12"
        assertEquals("2023-11-12", modèle.dateSélectionnée)
    }
    @Test
    fun `Étant donné un objet Modèle,lorsque nous accordons une heure sélectionnée,on obtient l'heure sélectionnée attendue`(){
        val modèle = Modèle()
        modèle.heureSélectionnée = "14:30"
        assertEquals("14:30", modèle.heureSélectionnée)
    }

    @Test
    fun `Étant donné un objet Modèle,lorsque nous affectons des places sélectionnées,on obtient les places sélectionnées attendues`(){
        val modèle = Modèle()
        modèle.placesSélectionnées = listOf("Place 1", "Place 2")
        assertEquals(listOf("Place 1", "Place 2"), modèle.placesSélectionnées)
    }

}
<<<<<<< HEAD:reservation_local2/app/src/test/java/com/example/reservation_local/ModèleFiltreTest.kt
@Test
fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
}
=======
>>>>>>> reservation_local2/app/src/test/java/com/example/reservation_local/ExampleUnitTest.kt
    @Test
    fun `Etant donner on doit ajouter la confirmation dans la base de donner on test la fun`() {

        val confirmation = SourceDonneConfirmation("Salle A", 2131230944, "4", "2023-01-01", "18:00")
        `when`(dbHelperMock.addConfirmation(confirmation)).thenReturn(1L)

        val resultat = modelDeConfirmation.addConfirmation(confirmation)

        verify(dbHelperMock).addConfirmation(confirmation)
        assertEquals(1L, resultat)
    }

    @Test
    fun `Étant donner il faut voir tous les affichages de la base de donner on test la fun AllConfirmation()`() {

        val listeAttendue = listOf(SourceDonneConfirmation("Salle A", 2131230944, "4", "2023-01-01", "18:00"))
        `when`(dbHelperMock.getAllConfirmations()).thenReturn(listeAttendue)


        val resultat = modelDeConfirmation.getAllConfirmations()


        verify(dbHelperMock).getAllConfirmations()
        assertEquals(listeAttendue, resultat)
    }
>>>>>>> a24654a08113eefb2cbace6893c128f69f11ecdc:reservation_local2/app/src/test/java/com/example/reservation_local/ExampleUnitTest.kt


