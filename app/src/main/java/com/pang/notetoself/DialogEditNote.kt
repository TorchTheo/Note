package com.pang.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogEditNote(val note: Note, val adapter: NoteAdapter): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_new_note, null)

        val editTitle = dialogView.findViewById<EditText>(R.id.editTitle)
        val editDescription = dialogView.findViewById<EditText>(R.id.editDescription)
        val editTime = dialogView.findViewById<TextView>(R.id.editTime)
//        val checkBoxIdea = dialogView.findViewById<CheckBox>(R.id.checkBoxIdea)
//        val checkBoxTodo = dialogView.findViewById<CheckBox>(R.id.checkBoxTodo)
//        val checkBoxImportant = dialogView.findViewById<CheckBox>(R.id.checkBoxImportant)
        val btnCancel = dialogView.findViewById<ImageButton>(R.id.btnCancel)
        val btnNewOK = dialogView.findViewById<ImageButton>(R.id.btnNewOK)
        editTitle.setText(note.title)
        editDescription.setText(note.des)
        editTime.setText(note.time)

        builder.setView(dialogView).setMessage("Add a new note")

        editTime.setOnFocusChangeListener { v, b ->
            if(b) {
                var ts: TimeSelect = TimeSelect()
                this.activity?.let { ts.showDatePickerDialog(it, v as TextView) }
            }
        }

        editTime.setOnClickListener { v ->
            var ts: TimeSelect = TimeSelect()
            this.activity?.let { ts.showDatePickerDialog(it, v as TextView) }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnNewOK.setOnClickListener {
            if(editTitle.text.toString() != "") {
                note.title = editTitle.text.toString()
            } else {
                Toast.makeText(this.context, "Title can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(editTime.text.toString() != "") {
                note.time = editTime.text.toString()
                note.d_time = note.timePattern.parse(note.time!!)
            } else {
                Toast.makeText(this.context, "Time can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            note.des = editDescription.text.toString()

            note.refreshTask()

            val callingActivity = activity as MainActivity?

            callingActivity!!.updateNote()

//            adapter.notifyDataSetChanged()

            dismiss()
        }


        return builder.create()
    }
}