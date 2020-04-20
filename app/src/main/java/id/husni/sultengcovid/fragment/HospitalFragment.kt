/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import id.husni.sultengcovid.R
import id.husni.sultengcovid.adapter.HospitalAdapter
import id.husni.sultengcovid.model.Hospital
import id.husni.sultengcovid.viewmodel.HospitalViewModel
import kotlinx.android.synthetic.main.fragment_hospital.*

/**
 * A simple [Fragment] subclass.
 */
class HospitalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HospitalAdapter(context)
        hospitalRecyclerView.adapter = adapter

        val viewModel : HospitalViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HospitalViewModel::class.java)
        viewModel.setHospitalData()
        showShimmer(true)
        viewModel.getHospitalData().observe(this,Observer<ArrayList<Hospital>>{
            adapter.setHospitals(it)
            showShimmer(false)
        })
    }

    private fun showShimmer(isShow: Boolean) {
        if(isShow){
            hospitalShimmer.showShimmer(true)
            hospitalShimmer.visibility = View.VISIBLE
        }
        else{
            hospitalShimmer.showShimmer(false)
            hospitalShimmer.visibility = View.GONE
        }
    }

}
