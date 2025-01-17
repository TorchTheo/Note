package com.pang.notetoself

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pang.notetoself.utils.Utils

class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: List<Note>
): RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var title = view.findViewById<TextView>(R.id.textViewTitle)
//        internal var description = view.findViewById<TextView>(R.id.textViewDescription)
//        internal var status = view.findViewById<TextView>(R.id.textViewStatus)
        internal var todo = view.findViewById<CheckBox>(R.id.todo)
        internal var des = view.findViewById<TextView>(R.id.briefDes)
        internal var time = view.findViewById<TextView>(R.id.briefTime)
        internal var count = 0

        init {
            view.isClickable = true
            view.setOnClickListener(this)

            Utils.setSize(title)

            Utils.setSize(des)
            Utils.setSize(time)

        }

        override fun onClick(view: View?) {
            mainActivity.showNote(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title
        holder.des.text = note.des
        holder.time.text = note.time
        holder.todo.setOnCheckedChangeListener { compoundButton, isChecked ->

            Log.i("info", "in BindViewHolder")
            if(note.done != isChecked)
                holder.count++
            note.done = isChecked
            // TODO: 更改复选框
            if (isChecked) {
                holder.itemView.setBackgroundResource(R.drawable.radius_check_yes)
            } else {
                holder.itemView.setBackgroundResource(R.drawable.radius_check_no)
            }
            (noteList as ArrayList).sortWith(compareBy({ note -> note.done }, { note -> note.d_time }))

            note.refreshTask()
            if(holder.count == 1) {
//                mainActivity.recreate()
                mainActivity.startActivity(Intent(mainActivity, MainActivity::class.java))
                mainActivity.finish()
                mainActivity.overridePendingTransition(0, 0)
                holder.count--
            }

        }

        holder.todo.isChecked = note.done

//        holder.description.text = if(note.description!!.length > 15) note.description!!.substring(0, 15) else note.description
//        when {
//            note.idea -> holder.status.text = mainActivity.resources.getString(R.string.idea_text)

//            note.important -> holder.status.text = mainActivity.resources.getString(R.string.important_text)

//            note.todo -> holder.status.text = mainActivity.resources.getString(R.string.todo_text)
//        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}