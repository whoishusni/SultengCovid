/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import id.husni.sultengcovid.R
import id.husni.sultengcovid.model.Province
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import id.husni.sultengcovid.widget.SultengCovidWidgetMain.Companion.WIDGET_ACTION
import id.husni.sultengcovid.widget.SultengCovidWidgetMain.Companion.WIDGET_ID
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Implementation of App Widget functionality.
 */
class SultengCovidWidgetMain : AppWidgetProvider() {
    companion object{
        const val WIDGET_ID = "widgetId"
        const val WIDGET_ACTION = "widgetId"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == WIDGET_ACTION){
            val views = RemoteViews(context?.packageName,R.layout.sulteng_covid_widget_main)
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetId = intent.getIntExtra(WIDGET_ID,0)
            Toast.makeText(context, "Updating Data",Toast.LENGTH_SHORT).show()
            if (context != null) {
                updateAppWidget(context,appWidgetManager,appWidgetId)
            }
            appWidgetManager.updateAppWidget(appWidgetId,views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.sulteng_covid_widget_main)
    val retrofit = RetrofitServiceApi.retrofit
    val endpoint = retrofit.create(ApiEndpoint::class.java)
    endpoint.getProvinceData()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { getWidgetData(appWidgetId,appWidgetManager,it,views) }
   // Instruct the widget manager to update the widget
    views.setOnClickPendingIntent(R.id.btnUpdateWidget, getSelfPending(context,appWidgetId,WIDGET_ACTION))
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getSelfPending(context: Context, appWidgetId: Int, action : String): PendingIntent? {
    val intent = Intent(context,SultengCovidWidgetMain::class.java)
    intent.putExtra(WIDGET_ID,appWidgetId)
    intent.action = action
    return PendingIntent.getBroadcast(context,100,intent,0)
}

fun getWidgetData(appWidgetId: Int, appWidgetManager: AppWidgetManager, it: Province?, views: RemoteViews) {
    views.setTextViewText(R.id.tvWidgetProvinceName, it?.dataProvince?.provinceName)
    views.setTextViewText(R.id.tvWidgetProvincePositive, it?.dataProvince?.provincePositive.toString())
    views.setTextViewText(R.id.tvWidgetProvinceRecovered, it?.dataProvince?.provinceRecovered.toString())
    views.setTextViewText(R.id.tvWidgetProvinceDeath, it?.dataProvince?.provinceDeath.toString())
    appWidgetManager.updateAppWidget(appWidgetId,views)
}
