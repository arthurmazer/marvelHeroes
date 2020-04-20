package com.mazer.marvelheroes.modules.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.marvelheroes.R
import com.mazer.marvelheroes.modules.listeners.HeroListener
import com.mazer.marvelheroes.modules.extensions.inflate
import com.mazer.marvelheroes.modules.models.Character
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroesAdapter(var listener: HeroListener?) : RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    private var listOfHeroes: ArrayList<Character> = arrayListOf()
    private var context: Context? = null

    fun addAllHeroes(heroesList: List<Character>){
        val sizeList = listOfHeroes.size
        listOfHeroes.addAll(heroesList)
        notifyItemRangeInserted(sizeList, heroesList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(inflate(R.layout.item_hero,parent))
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = listOfHeroes[position]
        val thumbnailUrl = hero.thumbnail.path + "." + hero.thumbnail?.extension

        holder.heroName.text = hero.name
        Glide.with(context ?: return)
            .load(thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.thumbnail)

        holder.itemView.setOnClickListener {
            listener?.onHeroClicked(hero.id, hero.name, thumbnailUrl)
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var heroName: TextView = view.findViewById(R.id.tv_hero_name)
        var thumbnail: ImageView = view.findViewById(R.id.iv_hero_photo)
    }
}
