package id.husni.sultengcovid

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
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
}
