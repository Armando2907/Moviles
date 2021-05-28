package ni.edu.uca.moviles2.arboretocarmelopalma.ui.events

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.repository.ArboretoRepository

@ExperimentalCoroutinesApi
class EventsViewModel
@ViewModelInject
constructor (private val repository: ArboretoRepository
): ViewModel() {



    // - Podemos poner un observador en los datos(en lugar de pedir los cambios) y solamente actualizar la UI
    //   cuando los datos cambian.
    // - Repositorio es completamente separado de la UI por medio del ViewModel.
    val allEvents: LiveData<List<EventEntity>> = repository.allEvents.asLiveData()
    /**
     * Incia una nueva coroutine para insertar data
     */

    fun insertEvent(event: EventEntity) = viewModelScope.launch {
        repository.insertEvent(event)
    }
    fun deleteAllEvents() = viewModelScope.launch {
        repository.deleteAllEvents()
    }

}

class HomeViewModelFactory(private val repository: ArboretoRepository) : ViewModelProvider.Factory {
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}