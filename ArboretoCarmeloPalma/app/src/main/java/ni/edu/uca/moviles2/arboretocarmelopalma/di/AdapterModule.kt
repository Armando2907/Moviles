package ni.edu.uca.moviles2.arboretocarmelopalma.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ni.edu.uca.moviles2.arboretocarmelopalma.adapters.EventListAdapter
import ni.edu.uca.moviles2.arboretocarmelopalma.adapters.TreeListAdapter
import javax.inject.Singleton

//Inyección de dependencias adptadores
@Module
@InstallIn(ApplicationComponent::class)
object AdapterModule {

    @Singleton
    @Provides
    fun provideEventApapter(application: Application): EventListAdapter {
        return EventListAdapter()
    }

    @Singleton
    @Provides
    fun provideTreeApapter(application: Application): TreeListAdapter {
        return TreeListAdapter()
    }

}