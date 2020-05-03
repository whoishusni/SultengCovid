/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */
package id.husni.sultengcovid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.District
import kotlinx.android.synthetic.main.district_item_holder.view.*

class DistrictAdapter(val context: Context?) :
    RecyclerView.Adapter<DistrictAdapter.ViewHolder>() {
    private val districtArray = ArrayList<District>()
    fun setData(items : ArrayList<District>){
        if (districtArray.size > 0){
            districtArray.clear()
        }
        districtArray.addAll(items)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(district: District) {
            with(itemView){
                tvDistrictName.text = district.districtName
                tvDistrictPositive.text = district.districtPositive.toString()
                tvDistrictNegative.text = district.districtNegative.toString()
                tvDistrictDeaths.text = district.districtDeaths.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictAdapter.ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.district_item_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return districtArray.size
    }

    override fun onBindViewHolder(holder: DistrictAdapter.ViewHolder, position: Int) {
        holder.bindData(districtArray[position])
    }

}