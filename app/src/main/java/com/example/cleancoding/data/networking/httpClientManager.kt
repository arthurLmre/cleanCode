package com.example.cleancoding.data.networking

import com.example.cleancoding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private class HttpClientManagerImp : HttpClientManager {

    private val okHttpClient = OkHttpClient
        .Builder()
        .apply {
            if (BuildConfig.DEBUG)
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
                )
        }
        .build()

    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}


interface HttpClientManager {
    val retrofit: Retrofit

    companion object {

        val instance: HttpClientManager = HttpClientManagerImp()

    }

}

inline fun <reified T> HttpClientManager.createApi(): T {
    return retrofit.create()
}