package ni.edu.uca.moviles2.arboretocarmelopalma.ui.services

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.ServiceEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.repository.ArboretoRepository

@ExperimentalCoroutinesApi
class ServicesViewModel
@ViewModelInject
constructor (private val repository: ArboretoRepository
): ViewModel() {



    // - Podemos poner un observador en los datos(en lugar de pedir los cambios) y solamente actualizar la UI
    //   cuando los datos cambian.
    // - Repositorio es completamente separado de la UI por medio del ViewModel.
    val allServices: LiveData<List<ServiceEntity>> = repository.allServices.asLiveData()
    /**
     * Incia una nueva coroutine para insertar data
     */

    fun insertService(service: ServiceEntity) = viewModelScope.launch {
        repository.insertService(service)
    }
    fun deleteAllServices() = viewModelScope.launch {
        repository.deleteAllServices()
    }

}

class HomeViewModelFactory(private val repository: ArboretoRepository) : ViewModelProvider.Factory {
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServicesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServicesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}