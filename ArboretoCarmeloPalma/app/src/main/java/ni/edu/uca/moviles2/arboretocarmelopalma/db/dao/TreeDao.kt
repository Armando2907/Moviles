package ni.edu.uca.moviles2.arboretocarmelopalma.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.moviles2.arboretocarmelopalma.db.entities.TreeEntity

/*
    Objeto de acceso a datos DAO (Data Access Object)
    Se especifican las consultas SQL y se asocia con la llamada a métodos
    Se usan anotaciones como "@Insert" para generar consultas sin especificar SQL
    DAO debe ser una interface o una clase abstracta
    Para observar cambios en los datos se usa Flow de las kotlinx-coroutines. Al agregar Flow al método Room genera el código necesario
    para actualizar el Flow cuando la base de datos es actualizada

 */
@Dao
interface TreeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTree(treeEntity : TreeEntity)

    @Delete()
    suspend fun deleteTree(treeEntity : TreeEntity)

    @Query("SELECT * FROM trees ORDER BY common_name")
    fun getTrees(): Flow<List<TreeEntity>>


    @Query("DELETE FROM trees")
    suspend fun deleteAllTrees()
}