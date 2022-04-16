package com.pang.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class DialogShowNote : DialogFragment() {

    private var note: Note? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater;
        val dialogView = inflater.inflate(R.layout.dialog_show_note, null)

        val txtTitle = dialogView.findViewById<TextView>(R.id.txtTitle)
        val txtDescription = dialogView.findViewById<TextView>(R.id.txtDescription)
        val showTime = dialogView.findViewById<TextView>(R.id.showTime)

        txtDescription.setMovementMethod(ScrollingMovementMethod.getInstance())

        txtTitle.text = note!!.title
        txtDescription.text = note!!.des
        showTime.text = note!!.time

//        val txtImportant = dialogView.findViewById<TextView>(R.id.textViewImportant)
//        val txtTodo = dialogView.findViewById<TextView>(R.id.textViewTodo)
//        val txtIdea = dialogView.findViewById<TextView>(R.id.textViewIdea)


        val btnShowOK = dialogView.findViewById<ImageButton>(R.id.btnShowOk)

        builder.setView(dialogView).setMessage("Your Note")
        btnShowOK.setOnClickListener{dismiss()}

        return builder.create()
    }

    fun sendNoteSelected(noteSelected: Note) {
        note = noteSelected
    }
}