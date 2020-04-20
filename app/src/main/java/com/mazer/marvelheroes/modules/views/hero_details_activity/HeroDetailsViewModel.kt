package com.mazer.marvelheroes.modules.views.hero_details_activity

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mazer.marvelheroes.modules.constants.IntentConstants
import com.mazer.marvelheroes.modules.models.Character
import com.mazer.marvelheroes.modules.models.Comic
import com.mazer.marvelheroes.modules.repositories.MarvelRepository
import com.mazer.marvelheroes.modules.models.Thumbnail

class HeroDetailsViewModel : ViewModel() {

    //extras
    private var heroId = 0
    private var heroName: String = ""
    private var thumbnailUrl: String = ""

    //live data
    val charLiveData = MutableLiveData<Character>()
    val comicsLiveData = MutableLiveData<List<Comic>?>()

    //repositories
    private val marvelRepository =
        MarvelRepository()

    var extras: Bundle? = null
        set(value) {
            field = value
            onSetExtras()
        }

    /**
     * manage activity extras
     */
    private fun onSetExtras() {
        heroId = extras?.getInt(IntentConstants.HERO_ID) ?: return
        heroName = extras?.getString(IntentConstants.HERO_NAME) ?: return
        thumbnailUrl = extras?.getString(IntentConstants.HERO_THUMBNAIL) ?: return

        loadHeroData()
    }

    /**
     * Get data from API comics and publish it to view
     */
    private fun loadHeroData() {
        //post value of data coming from extras
        charLiveData.postValue(
            Character(
                heroId,
                heroName,
                "",
                Thumbnail(thumbnailUrl, "")
            )
        )

        marvelRepository.fetchListAllComics(heroId, {
            //success call comics
            comicsLiveData.postValue(it.data.char)
        },{
            //fail api call comics
            comicsLiveData.postValue(null)
        })



    }
}