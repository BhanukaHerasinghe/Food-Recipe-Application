package com.example.androidcw2

import android.app.AlertDialog
import android.content.Context

// The function creates an alert dialog with the specified title and message, adds the stack trace to the message, and displays the dialog with an "OK" button.
object InformationforUser {
    fun errorShowing(title: String, message: String, stackTrace: String, context: Context){
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("$message Error: $stackTrace")
        alertDialogBuilder.setPositiveButton("OK"){dialog,which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}