package com.mazer.marvelheroes.data

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single


interface MarvelHeroesApi {

    @GET(MarvelEndpoints.CHARACTERS)
    fun getListOfAllHeroes(@Query("apikey") apiKey: String,
                           @Query("ts") ts: String,
                           @Query("hash") hash: String): Single<CharacterResponse>
}