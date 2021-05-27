package ni.edu.uca.moviles2.arboretocarmelopalma.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServicesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is service Fragment"
    }
    val text: LiveData<String> = _text
}