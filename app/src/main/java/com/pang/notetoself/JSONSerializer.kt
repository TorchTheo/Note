package com.pang.notetoself

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder

class JSONSerializer(private val filename: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(notes: List<Note>) {
        val jArray = JSONArray()

        for(n in notes)
            jArray.put(n.convert2JSON())

        var write: Writer? = null
        try {
            val out = context.openFileOutput(filename, Context.MODE_PRIVATE)
            write = OutputStreamWriter(out)
            write.write(jArray.toString())
        } finally {
            write?.close()
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Note> {
        val notes = ArrayList<Note>()

        var reader: BufferedReader? = null

        try {
            val `in` = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(`in`))
            val jsonString = StringBuilder()
            for(line in reader.readLine())
                jsonString.append(line)

            val jArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray
            for(i in 0 until jArray.length())
                notes.add(Note(jArray.getJSONObject(i)))
        } catch (e: FileNotFoundException) {}
        finally {
            reader!!.close()
        }
        return notes
    }

}