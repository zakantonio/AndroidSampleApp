package it.zakantonio.androidsampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.zakantonio.androidsampleapp.models.Cards

/**
 * ViewModel per gestire lo stato UI del FirstFragment.
 *
 * Il ViewModel serve a:
 * - Separare la logica di business dall'UI
 * - Mantenere i dati durante i cambi di configurazione (es. rotazione schermo)
 * - Esporre i dati all'UI tramite LiveData (pattern Observable)
 *
 * Importante: Il ViewModel sopravvive ai cambi di configurazione,
 * mentre il Fragment viene distrutto e ricreato.
 */
class MainViewModel : ViewModel() {

    // LiveData per il messaggio di benvenuto
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String> = _welcomeMessage

    // LiveData per la lista di carte
    // In questa lezione gestiamo una lista statica di nomi di carte
    private val _cards = MutableLiveData<List<Cards>>()
    val cards: LiveData<List<Cards>> = _cards

    init {
        // Inizializziamo la lista con alcune carte di esempio
        // Questa lista statica simula i dati che in futuro arriveranno dall'API
        loadCards()
    }

    /**
     * Elabora il nome del giocatore e aggiorna il messaggio di benvenuto.
     *
     * Questa logica è ora nel ViewModel invece che nel Fragment,
     * così è più facile da testare e riutilizzare.
     *
     * @param playerName nome inserito dall'utente
     * @param welcomeTemplate template del messaggio di benvenuto (dalla stringa resource)
     * @param emptyMessage messaggio da mostrare se il campo è vuoto
     */
    fun onStartGameClicked(playerName: String, welcomeTemplate: String, emptyMessage: String) {
        if (playerName.isNotBlank()) {
            // Se il nome è valido, creiamo il messaggio personalizzato
            val message = String.format(welcomeTemplate, playerName)
            _welcomeMessage.value = message
        } else {
            // Se il campo è vuoto, mostriamo il messaggio di errore
            _welcomeMessage.value = emptyMessage
        }
    }

    /**
     * Carica la lista di carte di esempio.
     *
     * Per ora usiamo dati statici (hardcoded).
     * Nelle prossime lezioni, queste carte arriveranno dall'API.
     */
    private fun loadCards() {
        val cardList = listOf(
            Cards("Asso di Quadri", R.drawable.quadri),
            Cards("Asso di Cuori", R.drawable.cuori),
            Cards("Asso di Fiori", R.drawable.fiori),
            Cards("Asso di Picche", R.drawable.picche),

        )
        _cards.value = cardList
    }
}
