package com.mazer.marvelheroes.modules.views.hero_details_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mazer.marvelheroes.R
import com.mazer.marvelheroes.modules.models.Character
import com.mazer.marvelheroes.modules.adapters.ComicsAdapter
import kotlinx.android.synthetic.main.activity_hero_details.*

class HeroDetailsActivity : AppCompatActivity() {

    private var viewModel: HeroDetailsViewModel? = null
    private val comicsAdapter =
        ComicsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.activity_hero_details_title)
        setContentView(R.layout.activity_hero_details)

        setupViewModel()
        setupView()
    }

    /**
     * setup viewmodel
     */
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(HeroDetailsViewModel::class.java)
        viewModel?.extras = intent.extras
    }

    /**
     * Setup view details
     */
    private fun setupView() {
        setupRecyclerView()
        registerObservers()
    }

    /**
     * observe live data changes
     */
    private fun registerObservers() {
        viewModel?.charLiveData?.observe(this, Observer {
            setupTopBanner(it)
        })
        viewModel?.comicsLiveData?.observe(this, Observer {
            comicsAdapter.addAllComics(it ?: return@Observer)
        })
    }


    /**
     * Setup the top banner of the activity that will display the hero name and his photo
     */
    private fun setupTopBanner(char: Character){
        tv_hero_name?.text = char.name

        Glide.with(this)
            .load(char.thumbnail.path)
            .placeholder(R.drawable.ic_launcher_background)
            .into(iv_hero_thumbnail)
    }

    private fun setupRecyclerView() {
        rv_comics.layoutManager = LinearLayoutManager(this)
        rv_comics.itemAnimator = DefaultItemAnimator()
        rv_comics.adapter = comicsAdapter
    }
}
