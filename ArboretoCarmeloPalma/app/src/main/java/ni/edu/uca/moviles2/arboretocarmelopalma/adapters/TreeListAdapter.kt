package ni.edu.uca.moviles2.arboretocarmelopalma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.moviles2.arboretocarmelopalma.R
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity

//Adaptador para la lista de arboles
class TreeListAdapter () : ListAdapter<TreeEntity, TreeListAdapter.TreeViewHolder>(EVENTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        return TreeViewHolder.create(parent)
    }
    //
    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.commonName,current.scientificName, current.family, current.image)
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("pagina" to current.url)

            Navigation.findNavController(holder.itemView).navigate(
                    R.id.nav_info_tree,
                    bundle)
        }
    }

    class TreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commonNameView: TextView = itemView.findViewById(R.id.common_name)
        private val scientificNameView: TextView = itemView.findViewById(R.id.scientific_name)
        private val familyView: TextView = itemView.findViewById(R.id.family)
        private val imView: ImageView = itemView.findViewById(R.id.icon_tree)

        fun bind(commonName: String, scientificName: String, family:String, image:String) {
            commonNameView.text = commonName
            scientificNameView.text = scientificName
            familyView.text= family
            imView.setImageResource(TreeViewHelper.getResIdByImage(image))
        }


        companion object {
            fun create(parent: ViewGroup): TreeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_tree_item, parent, false)
                return TreeViewHolder(view)
            }
        }
    }

    companion object {
        private val EVENTS_COMPARATOR = object : DiffUtil.ItemCallback<TreeEntity>() {
            override fun areItemsTheSame(oldItem: TreeEntity, newItem: TreeEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: TreeEntity, newItem: TreeEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}