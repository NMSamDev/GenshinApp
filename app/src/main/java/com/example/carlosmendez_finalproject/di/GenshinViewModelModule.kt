package com.example.carlosmendez_finalproject.di

import androidx.lifecycle.ViewModelProvider
import com.example.carlosmendez_finalproject.api.GenshinRepository
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(FragmentComponent::class)
object GenshinViewModelModule {

    @Provides
    fun provideGenshinViewModel(
        repository: GenshinRepository,
        dispatcher: CoroutineDispatcher
    ): GenshinViewModel {
        return GenshinViewModel(repository, dispatcher)
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

}