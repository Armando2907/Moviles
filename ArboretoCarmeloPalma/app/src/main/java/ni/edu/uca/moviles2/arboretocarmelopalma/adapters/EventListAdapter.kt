package ni.edu.uca.moviles2.arboretocarmelopalma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity
import java.text.SimpleDateFormat
import java.util.*

//Adaptador para la lista de eventos
class EventListAdapter () : ListAdapter<EventEntity, EventListAdapter.EventViewHolder>(EVENTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.create(parent)
    }
    //
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title,current.eventDate)

    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleItemView: TextView = itemView.findViewById(R.id.title_text)
        private val dateItemView: TextView = itemView.findViewById(R.id.date_text)

        fun bind(title: String, eventDate: Long ) {
            titleItemView.text = title
            dateItemView.text = convertLongToTime(eventDate)
        }

        private fun convertLongToTime(dateAdded: Long): CharSequence? {
            val date = Date(dateAdded)
            val format = SimpleDateFormat("yyyy.MM.dd")
            return format.format(date)
        }

        companion object {
            fun create(parent: ViewGroup): EventViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_event_item, parent, false)
                return EventViewHolder(view)
            }
        }
    }

    companion object {
        private val EVENTS_COMPARATOR = object : DiffUtil.ItemCallback<EventEntity>() {
            override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}