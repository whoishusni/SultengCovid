/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.Province
import id.husni.sultengcovid.viewmodel.ProvinceViewModel
import id.husni.sultengcovid.viewpager.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.app_name)
        setSupportActionBar(mainToolbar)

        mainViewPager.adapter = MainPagerAdapter(this,supportFragmentManager)
        mainTabLayout.setupWithViewPager(mainViewPager)

        val viewModel : ProvinceViewModel = ViewModelProvider(this, NewInstanceFactory()).get(ProvinceViewModel::class.java)
        viewModel.setProvinceData()
        viewModel.getProvinceLiveData().observe(this, Observer<Province> { provinceModel ->
            tvProvinceName.text = provinceModel.dataProvince.provinceName
            tvProvincePositive.text = provinceModel.dataProvince.provincePositive.toString()
            tvProvinceRecovered.text = provinceModel.dataProvince.provinceRecovered.toString()
            tvProvinceDeath.text = provinceModel.dataProvince.provinceDeath.toString()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuAbout ->{
                val intentToAbout : Intent = Intent(this, AboutActivity::class.java)
                startActivity(intentToAbout)
            }
            R.id.menuShare->{
                val intentToShare : Intent = Intent(Intent.ACTION_SEND)
                intentToShare.type = "text/plain"
                intentToShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
                startActivity(Intent.createChooser(intentToShare,"Share"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
