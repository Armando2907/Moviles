package ni.edu.uca.moviles2.arboretocarmelopalma.ui.trees

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.adapters.TreeListAdapter
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TreesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val treeViewModel: TreesViewModel by viewModels()

    @Inject
    lateinit var treeListAdapter: TreeListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Layout para este fragment
        val view = inflater.inflate(R.layout.fragment_trees, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.trees_list).apply {
            setHasFixedSize(true)
            // especificar el viewAdapter
            adapter = treeListAdapter
        }

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)

        // Agregar un observer del LiveData retornado por el repositorio.
        // El método onChanged() se ejecuta cuando la data observada cambia y la actividad
        // no está con enfoque
        treeViewModel.allTrees.observe(viewLifecycleOwner) { trees ->
            // Actualizar la copia cached de los trees en el adapter.
            trees.let { this.treeListAdapter.submitList(it) }
            if(trees.size==0) {
                Snackbar.make(view, resources.getString(R.string.empty_list), Snackbar.LENGTH_SHORT)
                        .show()
            }
        }


        return view
    }

}