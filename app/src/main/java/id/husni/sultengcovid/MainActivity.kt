package id.husni.sultengcovid

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import id.husni.sultengcovid.model.Province
import id.husni.sultengcovid.viewmodel.ProvinceViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        val viewModel : ProvinceViewModel = ViewModelProvider(this, NewInstanceFactory()).get(ProvinceViewModel::class.java)
        viewModel.setProvinceData()
        viewModel.getProvinceLiveData().observe(this, Observer<Province> { provinceModel ->
            tvProvinceName.text = provinceModel.dataProvince.provinceName
            tvProvincePositive.text = provinceModel.dataProvince.provincePositive
            tvProvinceRecovered.text = provinceModel.dataProvince.provinceRecovered
            tvProvinceDeath.text = provinceModel.dataProvince.provinceDeath
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
