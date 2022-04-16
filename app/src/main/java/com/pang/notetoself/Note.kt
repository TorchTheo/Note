package com.pang.notetoself

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {
        title = jo.getString(JSON_TITLE)
        done = jo.getBoolean(JSON_DONE)
        des = jo.getString(JSON_DESCRIPTION)
        time = jo.getString(JSON_TIME)

        d_time = timePattern.parse(time!!)
    }

    constructor() {}

    @Throws(JSONException::class)
    fun convert2JSON(): JSONObject {
        val jo = JSONObject()
        jo.put(JSON_TITLE, title)
        jo.put(JSON_DONE, done)
        jo.put(JSON_DESCRIPTION, des)
        jo.put(JSON_TIME, time)
        return jo
    }
}