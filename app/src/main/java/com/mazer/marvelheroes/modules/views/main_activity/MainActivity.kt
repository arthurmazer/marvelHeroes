package com.mazer.marvelheroes.modules.views.main_activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mazer.marvelheroes.R
import com.mazer.marvelheroes.modules.adapters.HeroesAdapter
import com.mazer.marvelheroes.modules.constants.IntentConstants
import com.mazer.marvelheroes.modules.listeners.HeroListener
import com.mazer.marvelheroes.modules.views.hero_details_activity.HeroDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HeroListener {

    private var mainViewModel: MainViewModel? = null
    private var heroesAdapter = HeroesAdapter(this)


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
        rv_heroes.layoutManager = GridLayoutManager(this, 2)
        rv_heroes.itemAnimator = DefaultItemAnimator()
        rv_heroes.adapter = heroesAdapter

        //add scroll listener to load more heroes when the user reached the last item
        rv_heroes?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItem = recyclerView.layoutManager?.childCount ?: return
                val totalItem = recyclerView.layoutManager?.itemCount ?: return
                val firtVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (dy > 0 && (visibleItem + firtVisibleItem) >= totalItem && firtVisibleItem >= 0 && totalItem >= 20) {
                    //reached last item, get next page
                    mainViewModel?.getNextPageHeroes()
                }
            }

        })
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun loadData(){
        //get first page
        mainViewModel?.getCharList()
    }

    private fun registerObservers(){
        mainViewModel?.charListLiveData?.observe(this, Observer {
            if (it != null){
                heroesAdapter.addAllHeroes(it)
            }else{
                //show error message
                Toast.makeText(this, getText(R.string.error_fetching_heroes), Toast.LENGTH_LONG).show()
            }
        })
        mainViewModel?.viewEventLiveData?.observe(this, Observer {
            if (it != null){
                when (it){
                    is MainViewModel.ViewEvent.StartLoadingMorePages -> {
                        progress_load_more_pages?.visibility = View.VISIBLE
                    }
                    is MainViewModel.ViewEvent.LoadingMorePagesFinished -> {
                        progress_load_more_pages?.visibility = View.GONE
                    }
                }
            }
        })
    }


    override fun onHeroClicked(id: Int, name: String, thumbnailUrl: String) {
        val intent = Intent(this, HeroDetailsActivity::class.java)
        intent.putExtra(IntentConstants.HERO_ID, id)
        intent.putExtra(IntentConstants.HERO_NAME, name)
        intent.putExtra(IntentConstants.HERO_THUMBNAIL, thumbnailUrl)
        startActivity(intent)
    }
}
