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
import id.husni.sultengcovid.adapter.DistrictAdapter
import id.husni.sultengcovid.model.District
import id.husni.sultengcovid.viewmodel.DistrictViewModel
import kotlinx.android.synthetic.main.fragment_district.*

/**
 * A simple [Fragment] subclass.
 */
class DistrictFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_district, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        districtRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = DistrictAdapter(context)
        districtRecyclerView.adapter = adapter

        val viewModel : DistrictViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DistrictViewModel::class.java)
        viewModel.setDistrictData()
        showShimmer(true)
        viewModel.getDistrictData().observe(this, Observer<ArrayList<District>> {
            adapter.setData(it)
            showShimmer(false)
        })

    }

    private fun showShimmer(isShow: Boolean) {
        if (isShow){
            districtShimmer.showShimmer(true)
            districtShimmer.visibility = View.VISIBLE
        }
        else{
            districtShimmer.showShimmer(false)
            districtShimmer.visibility = View.GONE
        }
    }

}
