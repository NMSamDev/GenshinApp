package com.example.carlosmendez_finalproject.di

import com.example.carlosmendez_finalproject.api.GenshinRepository
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {
    @Provides
    fun provideGenshinViewModel(
        repository: GenshinRepository,
        dispatcher: CoroutineDispatcher
    ): GenshinViewModel {
        return GenshinViewModel(repository, dispatcher)
    }
}