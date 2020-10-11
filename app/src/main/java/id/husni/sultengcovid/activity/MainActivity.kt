/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.Province
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import id.husni.sultengcovid.viewpager.MainPagerAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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

        val retrofit = RetrofitServiceApi.retrofit
        val endPoint = retrofit.create(ApiEndpoint::class.java)
        endPoint.getProvinceData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { getProvinceData(it) }
    }

    private fun getProvinceData(province: Province?) {
        tvProvinceName.text = province?.dataProvince?.provinceName
        tvProvincePositive.text = province?.dataProvince?.provincePositive.toString()
        tvProvinceRecovered.text = province?.dataProvince?.provinceRecovered.toString()
        tvProvinceDeath.text = province?.dataProvince?.provinceDeath.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAbout -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.menuPrevention -> startActivity(Intent(this, PreventionActivity::class.java))
            R.id.menuNews -> startActivity(Intent(this, NewsActivity::class.java))
            R.id.menuSetting -> startActivity(Intent(this, SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val alertDialogBuilder= AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit))
            .setMessage(getString(R.string.exit_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                finish()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
        val dialog : AlertDialog = alertDialogBuilder.create()
        dialog.show()

    }
}
