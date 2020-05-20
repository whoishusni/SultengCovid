/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.preference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import id.husni.sultengcovid.R
import id.husni.sultengcovid.receiver.DailyNotificationReceiver


class MainSettingPreference : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var DAILY_KEY : String
    private lateinit var dailySwitch : SwitchPreference
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_preference)
        initiate()
        summary()
    }

    private fun initiate(){
        DAILY_KEY = resources.getString(R.string.daily_notif_key)
        dailySwitch = findPreference<SwitchPreference>(DAILY_KEY) as SwitchPreference
    }

    private fun summary(){
        val sharedPreference : SharedPreferences = preferenceManager.sharedPreferences
        dailySwitch.isChecked = sharedPreference.getBoolean(DAILY_KEY, false)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val dailyNotificationReceiver = DailyNotificationReceiver()
        if (key == DAILY_KEY){
            if (dailySwitch.isChecked){
                dailyNotificationReceiver.setDailyNotification(context)
            }
            else{
                dailyNotificationReceiver.setCancelDailyNotification(context)
            }
        }
    }

}