package com.example.carlosmendez_finalproject.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.carlosmendez_finalproject.api.GenshinRepository
import com.example.carlosmendez_finalproject.api.GenshinRepositoryImpl
import com.example.carlosmendez_finalproject.api.GenshinService
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val TAG = "SingletonComponent" 
    private val service = Retrofit.Builder()
        .baseUrl("https://api.genshin.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(GenshinService::class.java)


    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRepository():GenshinRepository = GenshinRepositoryImpl(service)


    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    // ViewModelProvider.Factory es el que causa complicaciones
    fun provideViewModel(storeOwner: ViewModelStoreOwner): GenshinViewModel {
        return ViewModelProvider(storeOwner, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GenshinViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[GenshinViewModel::class.java]
    }
}