package com.pang.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.pang.notetoself.utils.Utils

class DialogShowNote(val adapter: NoteAdapter) : DialogFragment() {

    private var note: Note? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater;
        val dialogView = inflater.inflate(R.layout.dialog_show_note, null)

        val txtTitle = dialogView.findViewById<TextView>(R.id.txtTitle)
        Utils.setSize(txtTitle)
        val txtDescription = dialogView.findViewById<TextView>(R.id.txtDescription)
        Utils.setSize(txtDescription)
        val showTime = dialogView.findViewById<TextView>(R.id.showTime)
        Utils.setSize(showTime)

        val editNote = dialogView.findViewById<ImageButton>(R.id.editNote)
        val deleteNote = dialogView.findViewById<ImageButton>(R.id.deleteNode)

        txtDescription.setMovementMethod(ScrollingMovementMethod.getInstance())

        txtTitle.text = note!!.title
        txtDescription.text = note!!.des
        showTime.text = note!!.time

//        val txtImportant = dialogView.findViewById<TextView>(R.id.textViewImportant)
//        val txtTodo = dialogView.findViewById<TextView>(R.id.textViewTodo)
//        val txtIdea = dialogView.findViewById<TextView>(R.id.textViewIdea)


        val btnShowOK = dialogView.findViewById<ImageButton>(R.id.btnShowOk)

        builder.setView(dialogView)
        btnShowOK.setOnClickListener{dismiss()}
        editNote.setOnClickListener {
            val dialog = DialogEditNote(note!!, adapter)
            this.fragmentManager?.let { it1 -> dialog.show(it1, "123") }
            dismiss()
        }

        val callingActivity = activity as MainActivity?
        deleteNote.setOnClickListener {
            AlertDialog.Builder(activity)
                .setMessage(getString(R.string.delete_message))
                .setPositiveButton(getString(R.string.yes)) {_, _ ->
                    note!!.removeTask()

                    callingActivity!!.deleteNote(note!!)
                    if(isAdded) {
                        Toast.makeText(callingActivity, getString(R.string.delete_done), Toast.LENGTH_LONG).show()
                    }
                    dismiss()
                }
                .setNegativeButton(getString(R.string.no)) {_, _ ->
//                    dismiss()
                }.show()
        }

        return builder.create()
    }

    fun sendNoteSelected(noteSelected: Note) {
        note = noteSelected
    }
}