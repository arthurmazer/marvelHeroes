package com.mazer.marvelheroes.modules.api

import com.mazer.marvelheroes.modules.models.pojos.CharacterResponse
import com.mazer.marvelheroes.modules.models.pojos.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single


interface MarvelHeroesApi {

    @GET(MarvelEndpoints.CHARACTERS)
    fun getListOfAllHeroes(@Query("offset") offset: Int,
                           @Query("apikey") apiKey: String,
                           @Query("ts") ts: String,
                           @Query("hash") hash: String): Single<CharacterResponse>

    @GET(MarvelEndpoints.COMICS)
    fun getComics(@Path("hero_id") hero_id: Int,
                     @Query("apikey") apiKey: String,
                     @Query("ts") ts: String,
                     @Query("hash") hash: String): Single<ComicResponse>
}