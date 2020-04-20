/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.viewpager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.husni.sultengcovid.R
import id.husni.sultengcovid.fragment.DistrictFragment
import id.husni.sultengcovid.fragment.HospitalFragment

class MainPagerAdapter(private val context : Context, fm : FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    @StringRes
    private val PAGETITLE = intArrayOf(R.string.district,R.string.hospital)

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0-> fragment = DistrictFragment()
            1-> fragment = HospitalFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(PAGETITLE[position])
    }

    override fun getCount(): Int {
        return 2
    }

}