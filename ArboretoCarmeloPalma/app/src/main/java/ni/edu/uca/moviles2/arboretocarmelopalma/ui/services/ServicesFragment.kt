package ni.edu.uca.moviles2.arboretocarmelopalma.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ni.edu.uca.moviles2.arboretocarmelopalma.R

class ServicesFragment : Fragment() {

    private lateinit var servicesViewModel: ServicesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        servicesViewModel =
                ViewModelProvider(this).get(ServicesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_services, container, false)
        val textView: TextView = root.findViewById(R.id.text_services)
        servicesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}