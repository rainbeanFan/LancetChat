package cn.framework

import android.app.Activity
import android.graphics.Color
import android.view.View

class SystemUI {


    companion object {

        @JvmStatic
        fun fixSystemUI(activity: Activity){
            activity.window?.decorView!!.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            activity.window?.statusBarColor = Color.TRANSPARENT

        }

    }

}