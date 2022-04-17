package com.pang.notetoself

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pang.notetoself.utils.Utils
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Note {
    var title: String? = null
    var done: Boolean = false
    var des: String? = null
    var time: String? = null
    var d_time: Date? = null

    val timePattern = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    private val JSON_TITLE = "title"
    private val JSON_DESCRIPTION = "description"
    private val JSON_IDEA = "idea"
    private val JSON_DONE = "done"
    private val JSON_IMPORTANT = "important"
    private val JSON_TIME = "time"

    private inner class notifierTask(val note: Note): TimerTask() {
        override fun run() {
            var builder = NotificationCompat.Builder(Utils.getApplicationContext(), Utils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(note.title)
                .setContentText(note.des)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(Utils.getPendingIntent())
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(Utils.getApplicationContext())) {
                notify(notifier_id, builder.build())
            }
        }
    }

    private var notifier_task: notifierTask = notifierTask(this)
    private var notifier_id = 0

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {
        title = jo.getString(JSON_TITLE)
        done = jo.getBoolean(JSON_DONE)
        des = jo.getString(JSON_DESCRIPTION)
        time = jo.getString(JSON_TIME)

        d_time = timePattern.parse(time!!)

//        Timer().schedule(notifier_task, Date())
        createTask()
    }

    constructor() {
//        Timer().schedule(notifierTask(this), Date())
    }

    @Throws(JSONException::class)
    fun convert2JSON(): JSONObject {
        val jo = JSONObject()
        jo.put(JSON_TITLE, title)
        jo.put(JSON_DONE, done)
        jo.put(JSON_DESCRIPTION, des)
        jo.put(JSON_TIME, time)
        return jo
    }

    fun createTask() {
        if (!done and Date().before(d_time)) {
            notifier_id = Utils.getNotifyID()
            notifier_task = notifierTask(this)
            Timer().schedule(notifier_task, d_time)
        }
    }

    fun removeTask() {
        notifier_task.cancel()
        Timer().purge()

        NotificationManagerCompat.from(Utils.getApplicationContext()).cancel(notifier_id)
    }

    fun refreshTask() {
        removeTask()
        createTask()
    }
}