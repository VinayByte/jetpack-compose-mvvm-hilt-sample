package com.vinaybyte.sample.di

import com.vinaybyte.sample.BuildConfig
import com.vinaybyte.sample.Constants.BASE_URL
import com.vinaybyte.sample.data.datasource.remote.ApiService
import com.vinaybyte.sample.data.datasource.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        httpLoggingInterceptor?.let { interceptor ->
            builder.addNetworkInterceptor(interceptor)
        }
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.pingInterval(15, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(false)
        builder.followRedirects(false)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(okHttp: OkHttpClient, baseUrl: String): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        if (!BuildConfig.DEBUG) {
            return null
        }
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        service: ApiService
    ): Repository {
        return Repository(service)
    }
}