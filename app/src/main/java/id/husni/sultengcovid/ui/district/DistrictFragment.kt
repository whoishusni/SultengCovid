/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.ui.district

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.husni.sultengcovid.R
import id.husni.sultengcovid.data.source.remote.response.District
import kotlinx.android.synthetic.main.fragment_district.*

/**
 * A simple [Fragment] subclass.
 */
class DistrictFragment : Fragment(R.layout.fragment_district) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        districtRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = DistrictAdapter(context)
        districtRecyclerView.adapter = adapter

        val viewModel : DistrictViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            DistrictViewModel::class.java)
        viewModel.setDistrictData()
        showShimmer(true)
        viewModel.getDistrictData().observe(viewLifecycleOwner, Observer<ArrayList<District>> {
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
