package com.mazer.marvelheroes.data

import android.util.Log
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MarvelRepository {

    object Keys {
        const val MARVEL_API_PUBLIC_KEY = ""
        const val MARVEL_API_PRIVATE_KEY = ""
    }

     fun fetchListAllCharacters(onSuccess: (CharacterResponse) -> Unit, onFailure: (Throwable) -> Unit){
        val apiKey = Keys.MARVEL_API_PUBLIC_KEY
         val ts = System.currentTimeMillis().toString()
         val hash = ts + Keys.MARVEL_API_PRIVATE_KEY + apiKey

         Log.d("teste", apiKey)
         Log.d("teste", ts)
         Log.d("teste", hash)
         Log.d("teste", hash.toMD5())

         RetrofitHelper
            .getInstance()
            .getListOfAllHeroes(apiKey, ts, hash.toMD5())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailure(it)
            })
    }
}