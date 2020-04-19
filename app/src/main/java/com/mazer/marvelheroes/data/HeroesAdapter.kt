package com.mazer.marvelheroes.data

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mazer.marvelheroes.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    private var listOfHeroes: ArrayList<Character> = arrayListOf()
    private var context: Context? = null

    fun addAllHeroes(heroesList: List<Character>){
        listOfHeroes.clear()
        listOfHeroes.addAll(heroesList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflate(R.layout.item_hero, parent)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = listOfHeroes[position]

        Picasso.get().load(hero.thumbnail.path + "." + hero.thumbnail.extension).into(holder.thumbnail)
    }

    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {

        internal var thumbnail: ImageView = view.findViewById(R.id.iv_hero_photo)


    }


}