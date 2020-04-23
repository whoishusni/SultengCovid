/*
 * Copyright (c) 2020. 
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.serviceapi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.husni.sultengcovid.R

class FcmCloudMessageService : FirebaseMessagingService() {
    private var notifId = 1
    companion object{
        const val CHANNEL_ID = "channelOne"
        const val CHANNEL_NAME = "channelFcm"
        const val FCM_REQUEST_CODE = 100
        const val limitNotif = 2
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("NEW TOKEN", p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            getNotification(it.body)
            notifId++
        }
    }

    private fun getNotification(body: String?) {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this,FCM_REQUEST_CODE, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val uriSound : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder : NotificationCompat.Builder

        if (notifId < limitNotif){
            val largeIcon = BitmapFactory.decodeResource(resources,R.drawable.icon)
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText(body)
                .setSmallIcon(R.drawable.icon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
                .setSound(uriSound)
                .setLargeIcon(largeIcon)
        }
        else{
            val inboxStyle = NotificationCompat.InboxStyle()
                .setBigContentTitle("s")
                .setSummaryText(notifId.toString() + getString(R.string.message))
                .addLine(body)
                .addLine(body)

            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText(body)
                .setSmallIcon(R.drawable.icon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
                .setSound(uriSound)
                .setStyle(inboxStyle)
        }

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
            notificationBuilder.setChannelId(CHANNEL_ID)
        }
        val notification = notificationBuilder.build()
        notificationManager.notify(notifId,notification)
    }
}
