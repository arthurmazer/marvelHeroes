package com.mazer.marvelheroes.modules.api

object MarvelEndpoints {

    //base url
    const val BASE_URL = "https://gateway.marvel.com:443"

    //endpoints
    const val CHARACTERS = "/v1/public/characters"
    const val COMICS = "/v1/public/characters/{hero_id}/comics"
}