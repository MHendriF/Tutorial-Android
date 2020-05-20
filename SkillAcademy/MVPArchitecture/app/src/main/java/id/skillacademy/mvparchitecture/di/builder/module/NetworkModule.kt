package id.skillacademy.mvparchitecture.di.builder.module

import dagger.Module
import dagger.Provides
import id.skillacademy.mvparchitecture.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpAdapter(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(client)
            baseUrl(BuildConfig.TMDB_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providesHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}