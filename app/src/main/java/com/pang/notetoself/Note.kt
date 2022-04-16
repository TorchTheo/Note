package com.pang.notetoself

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
                .setContentTitle("In the note:")
                .setContentText(("This is a good day!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(Utils.getApplicationContext())) {
                notify(Utils.getNotifyID(), builder.build())
            }
        }
    }

    private var notifier_task: notifierTask = notifierTask(this)

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
        if (!done) {
            notifier_task = notifierTask(this)
            Timer().schedule(notifier_task, d_time)
        }
    }

    fun removeTask() {
        notifier_task.cancel()
        Timer().purge()
    }

    fun refreshTask() {
        removeTask()
        createTask()
    }
}