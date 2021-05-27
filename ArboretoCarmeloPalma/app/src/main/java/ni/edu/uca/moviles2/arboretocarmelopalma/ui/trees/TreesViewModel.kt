package ni.edu.uca.moviles2.arboretocarmelopalma.ui.trees

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.repository.ArboretoRepository

@ExperimentalCoroutinesApi
class TreesViewModel
@ViewModelInject
constructor (private val repository: ArboretoRepository
) : ViewModel() {


    // Usar LiveData y caching lo que allTrees regresa tienes muchos beneficios:
    // - Podemos poner un observador en los datos(en lugar de pedir los cambios) y solamente actualizar la UI
    //   cuando los datos cambian.
    // - Repositorio es completamente separado de la UI por medio del ViewModel.
    val allTrees: LiveData<List<TreeEntity>> = repository.allTrees.asLiveData()

    /**
     * Incia una nueva coroutine para insertar data
     */
    fun insert(tree: TreeEntity) = viewModelScope.launch {
        repository.insertTree(tree)
    }

    /**
     * Incia una nueva coroutine para borrar datos
     */
    fun delete(tree: TreeEntity) = viewModelScope.launch {
        repository.deleteTree(tree)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAllTrees()
    }

}

class TreesViewModelFactory(private val repository: ArboretoRepository) : ViewModelProvider.Factory {
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TreesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TreesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}