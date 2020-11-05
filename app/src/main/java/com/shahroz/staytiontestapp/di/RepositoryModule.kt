package com.shahroz.staytiontestapp.di

import com.shahroz.staytiontestapp.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(): Repository {
        return Repository()
    }
}
