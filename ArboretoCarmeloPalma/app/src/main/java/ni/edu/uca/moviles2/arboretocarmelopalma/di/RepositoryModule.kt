package ni.edu.uca.moviles2.arboretocarmelopalma.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.EventDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.TreeDao
import ni.edu.uca.moviles2.arboretocarmelopalma.repository.ArboretoRepository
import javax.inject.Singleton

//Inyección de dependencias repositorio
@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCatRepository(
            eventDao: EventDao,
            treeDao: TreeDao
    ): ArboretoRepository {
        return ArboretoRepository(eventDao,treeDao)
    }
}