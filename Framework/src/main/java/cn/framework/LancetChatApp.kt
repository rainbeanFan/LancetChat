package cn.framework

import android.app.Application

class LancetChatApp constructor() : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    companion object {

        @JvmStatic
        fun getInstance(): LancetChatApp {
            return SingletonHolder.INSTANCE
        }

    }

    private object SingletonHolder {
        val INSTANCE = LancetChatApp()
    }

}