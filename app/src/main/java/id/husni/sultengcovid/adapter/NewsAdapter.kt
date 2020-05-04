/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */
package id.husni.sultengcovid.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.News
import kotlinx.android.synthetic.main.news_item_holder.view.*

class NewsAdapter (val context : Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val newsArrayList = ArrayList<News>()

    fun setNewsArrayList(items : ArrayList<News>){
        if (newsArrayList.size > 0){
            newsArrayList.clear()
        }
        newsArrayList.addAll(items)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(news: News) {
            with(itemView){
                tvNewsTitle.text = news.newsTitle
                tvNewsSource.text = news.sourceNews.sourceName
                Glide.with(context)
                    .load(news.newsUrlToImage)
                    .into(imageNews)
                cvNewsHolder.setOnClickListener{
                    val url = news.newsUrl
                    val uriUrl = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uriUrl)
                    context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.news_item_holder,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bindData(newsArrayList[position])
    }
}