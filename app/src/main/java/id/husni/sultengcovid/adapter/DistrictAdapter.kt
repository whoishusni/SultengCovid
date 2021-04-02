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
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.district_item_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return districtArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(districtArray[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(district: District) {
            with(itemView){
                tvDistrictName.text = district.districtName
                tvDistrictPositive.text = district.districtPositive.toString()
                tvDistrictNegative.text = district.districtNegative.toString()
                tvDistrictDeaths.text = district.districtDeaths.toString()
                btnDistrictDetail.setOnClickListener{
                    val dialog = MaterialDialog(context, BottomSheet()).show {
                        customView(R.layout.bottomsheet_district_detail, scrollable = true)
                        positiveButton(android.R.string.ok)
                        setPeekHeight(res = R.dimen.bottom_sheet_peek_height)
                        cornerRadius(res = R.dimen.bottom_sheet_corner_radius)
                    }
                    val view = dialog.getCustomView()
                    val districtName : TextView= view.findViewById(R.id.tvDistrictNameDetail)
                    val odp : TextView = view.findViewById(R.id.tvDistrictOdpDetail)
                    val pdp : TextView = view.findViewById(R.id.tvDistrictPdpDetail)
                    val positive : TextView = view.findViewById(R.id.tvDistrictPositiveDetail)
                    val negative : TextView = view.findViewById(R.id.tvDistrictNegativeDetail)
                    val death : TextView = view.findViewById(R.id.tvDistrictDeathsDetail)

                    districtName.text = district.districtName
                    odp.text = context.getString(R.string.odp, district.districtOdp.toString())
                    pdp.text = context.getString(R.string.pdp, district.districtPdp.toString())
                    positive.text = context.getString(R.string.positive_bs, district.districtPositive.toString())
                    negative.text = context.getString(R.string.negative_bs, district.districtNegative.toString())
                    death.text = context.getString(R.string.death_bs, district.districtDeaths.toString())
                }
            }
        }

    }

}