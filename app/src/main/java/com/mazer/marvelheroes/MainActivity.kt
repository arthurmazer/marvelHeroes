package com.mazer.marvelheroes

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazer.marvelheroes.data.HeroesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null
    private var heroesAdapter = HeroesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
        loadData()
        registerObservers()
    }

    private fun setupRecyclerView() {
        rv_heroes.layoutManager = LinearLayoutManager(this)
        rv_heroes.itemAnimator = DefaultItemAnimator()
        rv_heroes.adapter = heroesAdapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun loadData(){
        mainViewModel?.getCharList()
    }

    private fun registerObservers(){
        mainViewModel?.charListLiveData?.observe(this, Observer {
            if (it != null){
                heroesAdapter.addAllHeroes(it)
            }else{
                //show error message
            }
        })
    }

    private fun calculateBestSpanCount(posterWidth: Int): Int {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val screenWidth = outMetrics.widthPixels.toFloat()
        return Math.round(screenWidth / posterWidth)
    }
}
