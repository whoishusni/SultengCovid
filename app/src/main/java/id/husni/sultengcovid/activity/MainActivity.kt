/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().subscribeToTopic("all")
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("Token", it.token)
        }

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
                val intentToAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentToAbout)
            }
            R.id.menuShare->{
                val intentToShare = Intent(Intent.ACTION_SEND)
                intentToShare.type = "text/plain"
                intentToShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
                startActivity(Intent.createChooser(intentToShare,"Share"))
            }
            R.id.menuPrevention->{
                val intentToPrevention = Intent(this, PreventionActivity::class.java)
                startActivity(intentToPrevention)
            }
            R.id.menuNews->{
                val intentToNews = Intent(this, NewsActivity::class.java)
                startActivity(intentToNews)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val alertDialogBuilder= AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit))
            .setMessage(getString(R.string.exit_message))
            .setPositiveButton(getString(R.string.yes),DialogInterface.OnClickListener { _, _ ->
                finish()
            })
            .setNegativeButton(getString(R.string.no), DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
        val dialog : AlertDialog = alertDialogBuilder.create()
        dialog.show()

    }
}
