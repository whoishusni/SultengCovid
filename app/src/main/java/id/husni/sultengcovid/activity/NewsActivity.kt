/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.husni.sultengcovid.R
import id.husni.sultengcovid.adapter.NewsAdapter
import id.husni.sultengcovid.model.News
import id.husni.sultengcovid.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setSupportActionBar(newsToolbar)
        supportActionBar?.title = getString(R.string.news)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val adapter = NewsAdapter(this)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = adapter

        val viewModel : NewsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NewsViewModel::class.java)
        viewModel.setNewsData()
        showShimmer(true)
        viewModel.getNewsData().observe(this, Observer<ArrayList<News>>{
            adapter.setNewsArrayList(it)
            showShimmer(false)
        })
    }

    private fun showShimmer(isShow: Boolean) {
        if(isShow){
            newsShimmer.visibility = View.VISIBLE
        }
        else{
            newsShimmer.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
