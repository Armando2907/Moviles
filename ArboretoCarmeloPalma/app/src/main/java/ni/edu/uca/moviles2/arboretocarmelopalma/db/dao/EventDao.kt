package ni.edu.uca.moviles2.arboretocarmelopalma.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.EventEntity

/*
    Objeto de acceso a datos DAO (Data Access Object)
    Se especifican las consultas SQL y se asocia con la llamada a métodos
    Se usan anotaciones como "@Insert" para generar consultas sin especificar SQL
    DAO debe ser una interface o una clase abstracta
    Para observar cambios en los datos se usa Flow de las kotlinx-coroutines. Al agregar Flow al método Room genera el código necesario
    para actualizar el Flow cuando la base de datos es actualizada

 */
@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(eventEntity : EventEntity)

    @Delete()
    suspend fun deleteEvent(eventEntity : EventEntity)

    @Query("SELECT * FROM events ORDER BY event_date DESC")
    fun getEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events ORDER BY event_date DESC LIMIT 3")
    fun getNextEvents(): Flow<List<EventEntity>>

    @Query("DELETE FROM events")
    suspend fun deleteAllEvents()
}