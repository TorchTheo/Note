package com.pang.notetoself.utils

import android.app.PendingIntent
import android.content.Context

object Utils {
    var appContext: Context? = null
    private var notifiyID = 10086
    private var pdIntent: PendingIntent? = null

    val CHANNEL_ID: String = "TODO-List-Channel"

    fun setApplicationContext(context: Context) {
        appContext = context
    }
    fun getApplicationContext(): Context {
        return appContext!!
    }
    fun getNotifyID(): Int {
        notifiyID += 1
        return notifiyID
    }
    fun setPendingIntent(pendingIntent: PendingIntent) {
        pdIntent = pendingIntent
    }
    fun getPendingIntent(): PendingIntent {
        return pdIntent!!
    }
}