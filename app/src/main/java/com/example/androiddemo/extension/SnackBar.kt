package com.example.androiddemo.extension

import android.support.design.widget.Snackbar
import android.view.View

fun View.SnackBarShort(hint: String, ActionHint: String, onClickListener: View.OnClickListener?) {
    Snackbar.make(this, hint, Snackbar.LENGTH_SHORT).setAction(ActionHint, onClickListener).show()
}

fun View.SnackBarLong(hint: String, ActionHint: String, onClickListener: View.OnClickListener?) {
    Snackbar.make(this, hint, Snackbar.LENGTH_LONG).setAction(ActionHint, onClickListener).show()
}
