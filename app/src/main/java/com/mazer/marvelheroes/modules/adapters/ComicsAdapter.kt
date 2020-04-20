package com.mazer.marvelheroes.modules.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.marvelheroes.R
import com.mazer.marvelheroes.modules.extensions.inflate
import com.mazer.marvelheroes.modules.models.Comic

class ComicsAdapter() : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    private var listOfComics: ArrayList<Comic> = arrayListOf()
    private var context: Context? = null

    fun addAllComics(heroesList: List<Comic>){
        listOfComics.clear()
        listOfComics.addAll(heroesList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflate(
            R.layout.item_comic,
            parent
        )
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfComics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = listOfComics[position]
        val thumbnailUrl = comic.thumbnail.path + "." + comic.thumbnail.extension

        holder.title.text = comic.title
        holder.description.text = comic.description
        holder.numPages.text = comic.pageCount.toString()

        Glide.with(context ?: return)
            .load(thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.thumbnail)

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tv_comic_title)
        var thumbnail: ImageView = view.findViewById(R.id.iv_comic_thumbnail)
        var description: TextView = view.findViewById(R.id.tv_comic_description)
        var numPages: TextView = view.findViewById(R.id.tv_comic_num_pages)
    }


}
