/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.ui.news

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.husni.sultengcovid.R
import id.husni.sultengcovid.data.source.remote.response.News
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setSupportActionBar(newsToolbar)
        supportActionBar?.apply {
            title = getString(R.string.news)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        val adapter = NewsAdapter(this)
        newsRecyclerView.adapter = adapter

        val viewModel : NewsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NewsViewModel::class.java)
        viewModel.setNewsData()
        showShimmer(true)
        viewModel.getNewsData().observe(this, Observer<ArrayList<News>>{
            adapter.setNewsArrayList(it)
            showShimmer(false)
        })
    }

    private fun showShimmer(isShow: Boolean) {
        newsShimmer.visibility = if (isShow) VISIBLE else GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
