package com.mazer.marvelheroes.modules.views.main_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mazer.marvelheroes.modules.models.Character
import com.mazer.marvelheroes.modules.repositories.MarvelRepository

class MainViewModel: ViewModel() {

    //live data
    val charListLiveData = MutableLiveData<List<Character>?>()
    val viewEventLiveData = MutableLiveData<ViewEvent>()
    //repositories
    private val marvelRepository =  MarvelRepository()
    //state variables
    private var currentOffset = 0
    private var currentLimit = 0

    sealed class ViewEvent {
        object StartLoadingMorePages: ViewEvent()
        object LoadingMorePagesFinished: ViewEvent()
    }

    fun getNextPageHeroes(){
        viewEventLiveData.postValue(ViewEvent.StartLoadingMorePages)
        currentOffset += currentLimit
        getCharList(currentOffset)
    }

    fun getCharList(offset: Int = 0){
        marvelRepository.fetchListAllCharacters(offset, {
            //success fetching marvel characters data
            if (it.code == 200) {
                currentLimit = it.data.limit
                currentOffset = it.data.offset
                charListLiveData.postValue(it.data.char)

                //if the page is not the first one
                if(offset > 0){
                    viewEventLiveData.postValue(ViewEvent.LoadingMorePagesFinished)
                }
            }else{
                charListLiveData.postValue(null)
            }
        },{
            //fail to fetch heroes data
            charListLiveData.postValue(null)
        })
    }
}