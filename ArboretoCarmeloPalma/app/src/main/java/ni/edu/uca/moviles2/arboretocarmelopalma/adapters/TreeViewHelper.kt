package ni.edu.uca.moviles2.arboretocarmelopalma.adapters

import ni.edu.uca.moviles2.arboretocarmelopalma.R

object TreeViewHelper {

    fun getResIdByImage(image: String?): Int {
        return when (image) {
            "ana" -> R.drawable.ana
            "bau" -> R.drawable.bau
            "bra" -> R.drawable.bra
            "can" -> R.drawable.can
            "car" -> R.drawable.car
            "cas" -> R.drawable.cas
            "cat" -> R.drawable.cat
            "cod" -> R.drawable.cod
            "eup" -> R.drawable.eup
            else -> R.drawable.ic_menu_trees
        }
    }
}