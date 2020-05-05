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
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.Hospital
import kotlinx.android.synthetic.main.hospital_item_holder.view.*

class HospitalAdapter (val context: Context?) :
    RecyclerView.Adapter<HospitalAdapter.ViewHolder>(){

    private val hospitalArray = ArrayList<Hospital>()

    fun setHospitals(items : ArrayList<Hospital>){
        if (hospitalArray.size > 0){
            hospitalArray.clear()
        }
        hospitalArray.addAll(items)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(hospital: Hospital) {
            with(itemView){
                tvHospitalName.text = hospital.hospitalName
                tvHospitalAddress.text = hospital.hospitalAddress
                btnHospitalCall.setOnClickListener{
                    val handPhone = hospital.hospitalPhone
                    val uriHandphone = Uri.parse("tel:$handPhone")
                    val phoneIntent = Intent(Intent.ACTION_DIAL,uriHandphone)
                    context.startActivity(phoneIntent)
                }
                btnHospitalEmail.setOnClickListener{
                    //TODO : EMAIL
                    Toast.makeText(context,"Under Construction", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.hospital_item_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(hospitalArray[position])
    }
}