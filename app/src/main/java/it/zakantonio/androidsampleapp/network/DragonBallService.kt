package it.zakantonio.androidsampleapp.network

import it.zakantonio.androidsampleapp.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Interfaccia Retrofit che dichiara gli endpoint della DragonBall API.
// Retrofit genera automaticamente l'implementazione a runtime.
interface DragonBallService {

    // Lista paginata di personaggi (prima pagina di default)
    @GET("characters")
    suspend fun getCharacters(): CharactersResponse

    // Lista di personaggi filtrata per razza
    @GET("characters")
    suspend fun getCharactersByRace(@Query("race") race: String): List<Character>

    // Dettaglio di un singolo personaggio per ID
    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}
