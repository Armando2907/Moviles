package ni.edu.uca.moviles2.arboretocarmelopalma.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.adapters.EventListAdapter
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var textViewHeader: TextView
    private lateinit var textViewSubHeader: TextView

    @Inject
    lateinit var eventListAdapter: EventListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Layout para este fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.events_list).apply {
            setHasFixedSize(true)
            // especificar el viewAdapter
            adapter = eventListAdapter
        }
        textViewHeader = view.findViewById(R.id.text_home)
        textViewHeader.setText(resources.getText(R.string.txt_home))
        textViewSubHeader = view.findViewById(R.id.text_description)
        textViewSubHeader.setText(resources.getText(R.string.txt_home_2))

        // Agregar un observer del LiveData retornado por el repositorio.
        // El método onChanged() se ejecuta cuando la data observada cambia y la actividad
        // no está con enfoque
        homeViewModel.nextEvents.observe(viewLifecycleOwner) { events ->
            // Actualizar la copia cached de los trees en el adapter.
            events.let { this.eventListAdapter.submitList(it) }
            if(events.size==0) {
                Snackbar.make(view, resources.getString(R.string.empty_list), Snackbar.LENGTH_SHORT)
                        .show()
            }
        }
        populateDataBase()
        return view
    }

    fun populateDataBase(){
        homeViewModel.insertEvent(EventEntity(1,"XVII Reunión de ecologistas","Universidad Centroamericana UCA", Date().time))
        homeViewModel.insertEvent(EventEntity(2,"Conferencia regional en relación a los efectos del clima en Arboretos","Universidad Centroamericana UCA", Date().time))
        homeViewModel.insertTree(TreeEntity(1,"Fabaceae", "Cassia fistula", "Caña fístula","cas","https://es.wikipedia.org/wiki/Cassia_fistula"))
        homeViewModel.insertTree(TreeEntity(2,"Acanthaceae", "Bravaisia integerrima", "Mangle blanco","bra","https://es.wikipedia.org/wiki/Bravaisia_integerrima"))
        homeViewModel.insertTree(TreeEntity(3,"Caricaceae", "Carica", "Papaya","car","https://es.wikipedia.org/wiki/Carica"))
        homeViewModel.insertTree(TreeEntity(4,"Annonaceae", "Cananga", "odorata","can","https://es.wikipedia.org/wiki/Cananga"))
        homeViewModel.insertTree(TreeEntity(5,"Bromeliaceae", "Ananas comusus", "Piña","ana","https://es.wikipedia.org/wiki/Ananas_comosus"))
        homeViewModel.insertTree(TreeEntity(6,"Euphorbiaceae", "Codiaeum variegatum", "Mango pintado","cod","https://es.wikipedia.org/wiki/Codiaeum_variegatum"))
        homeViewModel.insertTree(TreeEntity(7,"Fabaceae", "Bauhinia monandra", "Pata de vaca","bau","https://en.wikipedia.org/wiki/Bauhinia_monandra"))
        homeViewModel.insertTree(TreeEntity(8,"Apocynaceae", "Cataranthus roseus", "Primorosa","cat","https://es.wikipedia.org/wiki/Catharanthus_roseus"))
        homeViewModel.insertTree(TreeEntity(9,"Euphorbiaceae", "Euphorbia milii", "Corona de Cristo","eup","https://es.wikipedia.org/wiki/Euphorbia_milii"))

    }


}