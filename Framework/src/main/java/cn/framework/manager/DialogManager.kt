package cn.framework.manager

import android.content.Context
import android.view.Gravity
import cn.framework.R
import cn.framework.view.DialogView

class DialogManager private constructor() {

    companion object {

        @Volatile
        private var mInstance: DialogManager? = null

        @JvmStatic
        fun getInstance(): DialogManager {
            return mInstance ?: synchronized(this) {
                mInstance ?: DialogManager().also { mInstance = it }
            }
        }

    }

    fun initView(mContext: Context, layout: Int, gravity: Int = Gravity.CENTER) =
        DialogView(mContext, layout, R.style.Theme_Dialog, gravity)

    fun show(view: DialogView?) {
        if (view != null && !view!!.isShowing) view.show()
    }

    fun hide(view: DialogView?) {
        if (view != null && view!!.isShowing) view.dismiss()
    }

}