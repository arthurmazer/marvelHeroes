package com.mazer.marvelheroes.modules.listeners

interface HeroListener {
    fun onHeroClicked(id: Int, name: String, thumbnailUrl: String)
}