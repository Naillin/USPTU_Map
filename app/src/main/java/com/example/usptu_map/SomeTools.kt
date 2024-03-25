package com.example.usptu_map

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SomeTools(private val context: Context) {
    fun <T: EditText> isFieldsEmpty(textInputsList: ArrayList<T>): Boolean {
        var result: Boolean = false

        for(item in textInputsList) {
            if(item.text.isNullOrEmpty()) {
                result = true
            }
        }

        return result
    }

    public fun createSimpleDialog(thisActivity: AppCompatActivity, title: String = "default", message: String = "default") {
        val builder = AlertDialog.Builder(thisActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }

    public fun createYesNoDialog(
        thisActivity: AppCompatActivity,
        title: String = "default",
        message: String = "default",
        onYesClicked: () -> Unit,
        onNoClicked: () -> Unit
    ) {
        val builder = AlertDialog.Builder(thisActivity)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("YES") { _, _ ->
            onYesClicked.invoke()
        }

        builder.setNegativeButton("NO") { _, _ ->
            onNoClicked.invoke()
        }

        builder.create().show()
    }

    data class AlertDialogAction(
        val text: String,
        val listener: DialogInterface.OnClickListener
    )
    fun createAlertDialogMultiActions(
        title: String = "default",
        message: String = "default",
        vararg actions: AlertDialogAction
    ) {
        val builder = AlertDialog.Builder(context)
        title.let { builder.setTitle(it) }
        message.let { builder.setMessage(it) }

        actions.forEachIndexed { index, action ->
            when (index) {
                0 -> builder.setPositiveButton(action.text, action.listener)
                1 -> builder.setNegativeButton(action.text, action.listener)
                else -> builder.setNeutralButton(action.text, action.listener)
            }
        }

        builder.create().show()
    }

    fun createSnackbar(view: View, message: String = "default", duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, message, duration).show()
    }

    data class SnackbarAction(
        val text: String,
        val listener: (View) -> Unit
    )
    fun createSnackbarMultiActions(
        view: View,
        message: String = "default",
        duration: Int = Snackbar.LENGTH_LONG,
        vararg actions: SnackbarAction
    ) {
        val snackbar = Snackbar.make(view, message, duration)
        actions.forEach { action ->
            snackbar.setAction(action.text) { view ->
                action.listener(view)
            }
        }
        snackbar.show()
    }

}