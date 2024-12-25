package com.ovi.apileveltest.di

import android.content.Context
import com.ovi.apileveltest.data.api.FakeApiService
import com.ovi.apileveltest.data.reposotories.FakeRepository
import com.ovi.apileveltest.data.reposotories.FakeRepositoryImpl
import com.ovi.apileveltest.domain.FakeDataUseCase
import com.ovi.apileveltest.utils.BASE_URL
import com.ovi.apileveltest.utils.CurlLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    @Provides
    @Singleton
    fun provideStockRepository(api: FakeApiService): FakeRepository {
        return FakeRepositoryImpl(api)
    }

    @Provides
    fun provideFetchDataUseCase(repository: FakeRepository): FakeDataUseCase {
        return FakeDataUseCase(repository)
    }

    @Provides
    fun provideLoggerInterceptor() = HttpLoggingInterceptor()

    @Provides
    fun provideCurlLoggerInterceptor() = CurlLoggingInterceptor()

    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context = appContext

    private fun getRetrofit(loggerInterceptor: HttpLoggingInterceptor, curlLoggingInterceptor: CurlLoggingInterceptor): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(curlLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        @ApplicationContext context: Context,
        loggerInterceptor: HttpLoggingInterceptor,
        curlLoggingInterceptor: CurlLoggingInterceptor
    ): FakeApiService = getRetrofit(loggerInterceptor, curlLoggingInterceptor).create(FakeApiService::class.java)
}