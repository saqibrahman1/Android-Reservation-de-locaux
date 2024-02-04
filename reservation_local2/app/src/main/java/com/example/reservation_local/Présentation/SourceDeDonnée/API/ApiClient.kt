package com.example.reservation_local.Présentation.SourceDeDonnée.API
import android.util.Log
import com.example.reservation_local.Présentation.SourceDeDonnée.Réservation
import com.example.reservation_local.Présentation.SourceDeDonnée.Salle
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class ApiClient() {
    // Définissez une interface Retrofit interne pour les appels API.
    private interface ApiService {
        @GET("salles")
        suspend fun obtenirSalles(): List<Salle>

        @GET("reservations")
        suspend fun obtenirReservations(): List<Réservation>

        @POST("reservations")
        suspend fun ajouterReservation(@Body r: Réservation): Response<Réservation>

        @DELETE("reservations/{id}")
        suspend fun supprimerReservation(@Path("id") id: Int): Response<Réservation>

    }

    // Créez une instance de Retrofit et de ApiService au sein de la classe.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://6566a70864fcff8d730eefd3.mockapi.io/")
        // http://localhost:80
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    // Exposez une méthode publique pour obtenir les salles.
    suspend fun obtenirSalles(): List<Salle> {

        return try {
            service.obtenirSalles() // Utilisez l'instance de ApiService pour faire l'appel
        } catch (e: Exception) {
            // Gérer l'exception et retourner une liste vide ou gérer autrement
            Log.e("ApiClient", "Error fetching data: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun obtenirReservation(): List<Réservation>{
        return try {
            service.obtenirReservations()
        } catch (e: Exception) {
            Log.e("ApiClient", "Error fetching data: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun ajouterReservation(r: Réservation): Response<Réservation>{
        val response = service.ajouterReservation(r)
        return response
    }

    suspend fun supprimerReservation(id: Int): Response<Réservation>{
        return service.supprimerReservation(id)
    }

}