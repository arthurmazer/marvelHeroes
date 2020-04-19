package com.mazer.marvelheroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mazer.marvelheroes.data.Character
import com.mazer.marvelheroes.data.MarvelRepository

class MainViewModel: ViewModel() {

    val charListLiveData = MutableLiveData<List<Character>?>()
    private val marvelRepository = MarvelRepository()

    fun getCharList(){
        marvelRepository.fetchListAllCharacters({
            //success fetching marvel characters data
            if (it.code == 200) {
                charListLiveData.postValue(it.data.char)
            }else{
                charListLiveData.postValue(null)
            }
        },{
            //fail to fetch heroes data
            charListLiveData.postValue(null)
        })
    }
}