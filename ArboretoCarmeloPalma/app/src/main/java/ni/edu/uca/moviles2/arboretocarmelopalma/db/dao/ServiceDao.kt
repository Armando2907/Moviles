package ni.edu.uca.moviles2.arboretocarmelopalma.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.ServiceEntity

/*
    Objeto de acceso a datos DAO (Data Access Object)
    Se especifican las consultas SQL y se asocia con la llamada a métodos
    Se usan anotaciones como "@Insert" para generar consultas sin especificar SQL
    DAO debe ser una interface o una clase abstracta
    Para observar cambios en los datos se usa Flow de las kotlinx-coroutines. Al agregar Flow al método Room genera el código necesario
    para actualizar el Flow cuando la base de datos es actualizada

 */
@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertService(serviceEntity : ServiceEntity)

    @Delete()
    suspend fun deleteService(serviceEntity : ServiceEntity)

    @Query("SELECT * FROM services ORDER BY id")
    fun getServices(): Flow<List<ServiceEntity>>

    @Query("DELETE FROM services")

    suspend fun deleteAllServices()
}