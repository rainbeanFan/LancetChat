package cn.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.framework.SystemUI

abstract class BaseUIActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUI.fixSystemUI(this)
    }

}