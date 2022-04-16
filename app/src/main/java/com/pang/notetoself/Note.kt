package com.pang.notetoself

import org.json.JSONException
import org.json.JSONObject

class Note {
    var title: String? = null
    var done: Boolean = false

    private val JSON_TITLE = "title"
    private val JSON_DESCRIPTION = "description"
    private val JSON_IDEA = "idea"
    private val JSON_DONE = "DONE"
    private val JSON_IMPORTANT = "important"

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {
        title = jo.getString(JSON_TITLE)
        done = jo.getBoolean(JSON_DONE)
    }

    constructor() {}

    @Throws(JSONException::class)
    fun convert2JSON(): JSONObject {
        val jo = JSONObject()
        jo.put(JSON_TITLE, title)
        jo.put(JSON_DONE, done)
        return jo
    }
}