package com.mazer.marvelheroes.modules.repositories

import com.mazer.marvelheroes.BuildConfig
import com.mazer.marvelheroes.modules.models.pojos.CharacterResponse
import com.mazer.marvelheroes.modules.models.pojos.ComicResponse
import com.mazer.marvelheroes.modules.api.RetrofitHelper
import com.mazer.marvelheroes.modules.extensions.toMD5
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MarvelRepository {

    object Keys {
        const val MARVEL_API_PUBLIC_KEY = BuildConfig.MARVEL_API_PUBLIC_KEY
        const val MARVEL_API_PRIVATE_KEY = BuildConfig.MARVEL_API_PRIVATE_KEY
    }

     fun fetchListAllCharacters(offset: Int = 0, onSuccess: (CharacterResponse) -> Unit, onFailure: (Throwable) -> Unit){
         val apiKey = Keys.MARVEL_API_PUBLIC_KEY
         val ts = System.currentTimeMillis().toString()
         val hash = ts + Keys.MARVEL_API_PRIVATE_KEY + apiKey

         RetrofitHelper.getInstance()
            .getListOfAllHeroes(offset, Keys.MARVEL_API_PUBLIC_KEY, ts, hash.toMD5())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }

    fun fetchListAllComics(heroId: Int, onSuccess: (ComicResponse) -> Unit, onFailure: (Throwable) -> Unit){
        val apiKey =
            Keys.MARVEL_API_PUBLIC_KEY
        val ts = System.currentTimeMillis().toString()
        val hash = ts + Keys.MARVEL_API_PRIVATE_KEY + apiKey

        RetrofitHelper.getInstance()
            .getComics(heroId,
                Keys.MARVEL_API_PUBLIC_KEY, ts, hash.toMD5())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }
}