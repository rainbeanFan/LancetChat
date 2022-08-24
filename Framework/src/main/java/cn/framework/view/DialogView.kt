package cn.framework.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager

class DialogView : Dialog {

    constructor(context: Context, layout: Int, style: Int, gravity: Int) : super(context, style) {
        setContentView(layout)
        val layoutParams = window?.attributes.apply {
            this!!.width = WindowManager.LayoutParams.MATCH_PARENT
            this!!.height = WindowManager.LayoutParams.WRAP_CONTENT
            this!!.gravity = gravity
        }
        window?.attributes = layoutParams
    }

}