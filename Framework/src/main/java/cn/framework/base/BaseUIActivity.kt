package cn.framework.base

import android.os.Bundle
import cn.framework.util.SystemUI

abstract class BaseUIActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUI.fixSystemUI(this)
    }

}