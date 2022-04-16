package com.pang.notetoself.utils

import android.content.Context

object Utils {
    var appContext: Context? = null
    private var notifiyID = 10086

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
}