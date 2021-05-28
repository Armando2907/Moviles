package ni.edu.uca.moviles2.arboretocarmelopalma.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ni.edu.uca.moviles2.arboretocarmelopalma.db.ArboretoDatabase
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.EventDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.ServiceDao
import ni.edu.uca.moviles2.arboretocarmelopalma.db.dao.TreeDao
import javax.inject.Singleton

//Inyección de dependencias modulo de base de datos
@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): ArboretoDatabase {
        return Room
            .databaseBuilder(context, ArboretoDatabase::class.java, ArboretoDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideEventDao(arboretoDatabase: ArboretoDatabase): EventDao {
        return arboretoDatabase.eventDao()
    }

    @Singleton
    @Provides
    fun provideTreeDao(arboretoDatabase: ArboretoDatabase): TreeDao {
        return arboretoDatabase.treeDao()
    }

    @Singleton
    @Provides
    fun provideServiceDao(arboretoDatabase: ArboretoDatabase): ServiceDao {
        return arboretoDatabase.serviceDao()
    }
}