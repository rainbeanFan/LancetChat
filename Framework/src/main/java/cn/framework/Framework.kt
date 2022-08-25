package cn.framework

import android.content.Context
import cn.framework.bmob.BmobManager
import cn.framework.util.SPUtils

class Framework private constructor(){

    companion object{

        private var instance: Framework?=null

        @JvmStatic
        fun getInstance(): Framework {
            instance ?: synchronized(this){
                instance ?: Framework().also { instance = it }
            }
            return instance!!
        }

    }

    fun initFramework(context: Context){
        SPUtils.getInstance()!!.initSP(context)
        BmobManager.getInstance().initBmob(context)


    }


}