package ni.edu.uca.moviles2.arboretocarmelopalma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.EventDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.TreeDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity


@Database(entities = [TreeEntity::class,EventEntity::class], version = 1)
abstract class ArboretoDatabase : RoomDatabase(){
    companion object{
        val DATABASE_NAME = "ArboretoDB"
    }
    //La base de datos expone los DAOs por medio de metodos getter para cada @Dao
    abstract fun eventDao(): EventDao
    abstract fun treeDao(): TreeDao
}