package com.pang.notetoself.utils

import android.app.PendingIntent
import android.content.Context
import android.view.View
import android.widget.TextView

object Utils {
    var fontScale: Float? = null

    var appContext: Context? = null
    private var notifiyID = 10086
    private var pdIntent: PendingIntent? = null

    val CARING_MOD_SCALE = 2.0f

    val NORMAL_MOD_SCALE = 1.0f

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

    fun setSize(t: TextView) {
        t.setTextSize(0, t.textSize * fontScale!!)
    }
}