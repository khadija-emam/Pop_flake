package com.project.pop_flake.di


import android.util.Log
import com.project.pop_flake.data.remote.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://imdb-api.com/"
const val API_PUBLIC="k_eul8hc5t"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    // Makes Hilt provide Retrofit instance when a Retrofit type is requested
    @Provides
    @Singleton
    fun providesRetrofit( okHttpClient: OkHttpClient): RetrofitService {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(30000, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(30000, TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        val token = API_PUBLIC
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient
            .addInterceptor(logging)

        token.let {
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addPathSegment(it)
                    .build()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)

            }
        }

        return httpClient.build()
    }


}