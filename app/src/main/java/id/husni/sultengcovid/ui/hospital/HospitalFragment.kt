/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.ui.hospital

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.husni.sultengcovid.R
import id.husni.sultengcovid.data.source.remote.response.Hospital
import kotlinx.android.synthetic.main.fragment_hospital.*

/**
 * A simple [Fragment] subclass.
 */
class HospitalFragment : Fragment(R.layout.fragment_hospital) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HospitalAdapter(context)
        hospitalRecyclerView.adapter = adapter

        val viewModel : HospitalViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            HospitalViewModel::class.java)
        viewModel.setHospitalData()
        showShimmer(true)
        viewModel.getHospitalData().observe(viewLifecycleOwner,Observer<ArrayList<Hospital>>{
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
