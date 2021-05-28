package ni.edu.uca.moviles2.arboretocarmelopalma.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.adapters.EventListAdapter
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EventsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val eventViewModel: EventsViewModel by viewModels()

    @Inject
    lateinit var eventListAdapter: EventListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Layout para este fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.events_list).apply {
            setHasFixedSize(true)
            // especificar el viewAdapter
            adapter = eventListAdapter
        }

        // Agregar un observer del LiveData retornado por el repositorio.
        // El método onChanged() se ejecuta cuando la data observada cambia y la actividad
        // no está con enfoque
        eventViewModel.allEvents.observe(viewLifecycleOwner) { events ->
            // Actualizar la copia cached de los trees en el adapter.
            events.let { this.eventListAdapter.submitList(it) }
            if(events.size==0) {
                Snackbar.make(view, resources.getString(R.string.empty_list), Snackbar.LENGTH_SHORT)
                        .show()
            }
        }


        return view
    }

}