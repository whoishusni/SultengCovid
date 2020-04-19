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
        viewModel.getDistrictData().observe(this, Observer<ArrayList<District>> {
            adapter.districts = it
        })

    }

}
