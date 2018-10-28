package com.paraxco.basictools.imageTools.dialog

import android.widget.TextView
import android.widget.Toast
import com.paraxco.basictools.R
import com.paraxco.commontools.Utils.SmartLogger
import com.paraxco.imagetools.Dialog.YesNoDialogBase

class MyCustomDialog : YesNoDialogBase(R.layout.test_dialog) {
    override fun getDialogNote() = R.string.dialog_note
    override fun doYesAction() {
        SmartLogger.logDebug("yes")
        dialog?.dismiss()
    }

    override fun initDialog() {
        dialog?.findViewById<TextView>(com.paraxco.imagetools.R.id.txtNote)?.setOnClickListener {
            Toast.makeText(dialog?.context, "hello", Toast.LENGTH_SHORT).show()
        }

        super.initDialog()
    }

    override fun getDialogTitle() = R.string.dialog_title
}