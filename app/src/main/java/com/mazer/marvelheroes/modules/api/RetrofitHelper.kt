package com.mazer.marvelheroes.modules.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getInstance(): MarvelHeroesApi {
        val retrofit =
            createRetrofitInstance()
        return retrofit.create(MarvelHeroesApi::class.java)
    }

    private fun createRetrofitInstance(): Retrofit {
        val builder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)

        val client = builder.build()
        val gsonBuilder = GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation()
        return Retrofit.Builder()
            .baseUrl(MarvelEndpoints.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()
    }
}