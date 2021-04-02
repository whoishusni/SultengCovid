/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import id.husni.sultengcovid.R
import id.husni.sultengcovid.activity.MainActivity
import id.husni.sultengcovid.model.Province
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class DailyNotificationReceiver : BroadcastReceiver() {
    private lateinit var notifTitle : String
    companion object{
        const val DAILY_NOTIF_REQUEST_CODE = 100
        const val DAILY_NOTIF_REQUEST_CODE_TO_INTENT = 200
        const val NOTIFID = 1
        const val CHANNEL_ID_DAILY = "channel_id_daily"
        const val CHANNEL_ID_NAME = "channel_id_daily_name"
        const val EXTRA_MESSAGE_DATA = "Extra_message_data"
    }
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE_DATA)
        showNotification(context,message)
    }

    fun setDailyNotification(context: Context?){
        val retrofit = RetrofitServiceApi.retrofit
        val endPoint = retrofit.create(ApiEndpoint::class.java)
        endPoint.getProvinceData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { setAlarm(context, it) }
    }

    private fun setAlarm(context: Context?, it: Province) {
        notifTitle = context?.getString(R.string.message_notif,
            it.dataProvince.provincePositive.toString(),
            it.dataProvince.provinceRecovered.toString(),
            it.dataProvince.provinceDeath.toString()).toString()
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,8)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)

        val intent = Intent(context, DailyNotificationReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE_DATA,notifTitle)
        val pendingIntent = PendingIntent.getBroadcast(context,DAILY_NOTIF_REQUEST_CODE,intent,0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(context,context.getString(R.string.daily_notif_setup),Toast.LENGTH_SHORT).show()
    }

    fun setCancelDailyNotification(context: Context?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, DAILY_NOTIF_REQUEST_CODE, intent,0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, context.getString(R.string.daily_notif_cancel), Toast.LENGTH_SHORT).show()
    }

    private fun showNotification(context: Context, message: String?) {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //date
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = simpleDateFormat.format(date.time)

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, DAILY_NOTIF_REQUEST_CODE_TO_INTENT,intent,PendingIntent.FLAG_ONE_SHOT)
        val sound : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationCompat = NotificationCompat.Builder(context,CHANNEL_ID_DAILY)
            .setContentTitle("Update Covid-19 : $formattedDate")
            .setContentText(message)
            .setSound(sound)
            .setSmallIcon(R.drawable.ic_icon)
            .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel(CHANNEL_ID_DAILY, CHANNEL_ID_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000,1000,1000,1000,1000)
            notificationCompat.setChannelId(CHANNEL_ID_DAILY)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = notificationCompat.build()
        notificationManager.notify(NOTIFID, notification)

    }
}
