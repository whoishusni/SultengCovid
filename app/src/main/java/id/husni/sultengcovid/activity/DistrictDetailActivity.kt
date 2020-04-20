/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.District
import kotlinx.android.synthetic.main.activity_district_detail.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DistrictDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PARSING_DATA: String = "extra_parsing_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district_detail)
        setSupportActionBar(districtDetailToolbar)
        supportActionBar?.title = getString(R.string.detail)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val districtModel : District = intent.getParcelableExtra(EXTRA_PARSING_DATA)

        tvDistrictNameDetail.text = districtModel.districtName
        tvDistrictOdpDetail.text = "${resources.getString(R.string.odp)} : ${districtModel.districtOdp}"
        tvDistrictPdpDetail.text = "${resources.getString(R.string.pdp)} : ${districtModel.districtPdp}"
        tvDistrictPositiveDetail.text = "${resources.getString(R.string.positive)} : ${districtModel.districtPositive}"
        tvDistrictNegativeDetail.text = "${resources.getString(R.string.negative)} : ${districtModel.districtNegative}"
        tvDistrictDeathsDetail.text = "${resources.getString(R.string.death)} : ${districtModel.districtDeaths}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
