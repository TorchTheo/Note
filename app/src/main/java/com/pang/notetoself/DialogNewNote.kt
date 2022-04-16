package com.pang.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment

class DialogNewNote(val adapter: NoteAdapter) : DialogFragment() {
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
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnNewOK = dialogView.findViewById<Button>(R.id.btnNewOK)

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
            val newNote = Note()
            if(editTitle.text.toString() != "") {
                newNote.title = editTitle.text.toString()
            } else {
                Toast.makeText(this.context, "Title can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(editTime.text.toString() != "") {
                newNote.time = editTime.text.toString()
                newNote.d_time = newNote.timePattern.parse(newNote.time!!)
            } else {
                Toast.makeText(this.context, "Time can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            newNote.des = editDescription.text.toString()

            newNote.createTask()

            val callingActivity = activity as MainActivity?

            callingActivity!!.createNewNote(newNote)

            dismiss()
        }



        return builder.create()
    }
}