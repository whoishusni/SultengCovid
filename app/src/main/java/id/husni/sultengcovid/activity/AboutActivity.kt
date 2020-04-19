/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.husni.sultengcovid.BuildConfig
import id.husni.sultengcovid.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(aboutToolbar)
        supportActionBar?.title = resources.getString(R.string.detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        tvAppVersion.text = BuildConfig.VERSION_NAME

        tvAboutGithub.setOnClickListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.tvAboutGithub){
            val url = "https://www.github.com/whoishusni"
            val uri : Uri = Uri.parse(url)
            val intentToGithub : Intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intentToGithub)
        }
    }
}
