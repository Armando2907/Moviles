package ni.edu.uca.moviles2.arboretocarmelopalma.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.EventDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.TreeDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity


/*
    Repositorio provee acceso a multiples fuentes de datos
    Declara el DAO como propiedad private en el constructor
 */
class ArboretoRepository (private val eventDao: EventDao, private val treeDao: TreeDao){

    // Room ejecuta todos los queries en hilos separados.
    // Flow will notifica al observer cuando los datos cambian.
    val nextEvents: Flow<List<EventEntity>> = eventDao.getNextEvents()
    val allEvents: Flow<List<EventEntity>> = eventDao.getEvents()
    val allTrees: Flow<List<TreeEntity>> = treeDao.getTrees()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteEvent(event: EventEntity) {
        eventDao.deleteEvent(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllEvents() {
        eventDao.deleteAllEvents()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTree(tree: TreeEntity) {
        treeDao.insertTree(tree)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteTree(tree: TreeEntity) {
        treeDao.deleteTree(tree)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllTrees() {
        treeDao.deleteAllTrees()
    }

}