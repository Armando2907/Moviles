package ni.edu.uca.moviles2.arboretocarmelopalma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.ServiceEntity

//Adaptador para la lista de servicios
class ServiceListAdapter () : ListAdapter<ServiceEntity, ServiceListAdapter.ServiceViewHolder>(SERVICES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder.create(parent)
    }
    //
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title,current.description)

    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleItemView: TextView = itemView.findViewById(R.id.title_text)
        private val descItemView: TextView = itemView.findViewById(R.id.desc_text)

        fun bind(title: String, desc: String ) {
            titleItemView.text = title
            descItemView.text = desc
        }


        companion object {
            fun create(parent: ViewGroup): ServiceViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_service_item, parent, false)
                return ServiceViewHolder(view)
            }
        }
    }

    companion object {
        private val SERVICES_COMPARATOR = object : DiffUtil.ItemCallback<ServiceEntity>() {
            override fun areItemsTheSame(oldItem: ServiceEntity, newItem: ServiceEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ServiceEntity, newItem: ServiceEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}