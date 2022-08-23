package cn.framework.util

import android.content.Context
import android.content.SharedPreferences
import cn.framework.BuildConfig


class SPUtils private constructor() {

    private var mSp: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    companion object {

        private var instance: SPUtils? = null

        @JvmStatic
        fun getInstance(): SPUtils? {
            instance ?: synchronized(this) {
                instance ?: SPUtils().also { instance = it }
            }
            return instance
        }

    }

    fun initSP(context: Context) {
        mSp = context.getSharedPreferences(BuildConfig.SP_NAME, Context.MODE_PRIVATE)
        mEditor = mSp?.edit()
    }

    fun putInt(key: String, value: Int) {
        mEditor?.putInt(key, value)
        mEditor?.commit()
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return mSp?.getInt(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        mEditor?.putString(key, value)
        mEditor?.commit()
    }

    fun getString(key: String, defaultValue: String): String? {
        return mSp?.getString(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        mEditor?.putBoolean(key, value)
        mEditor?.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mSp?.getBoolean(key, defaultValue) ?: false
    }

    fun deleteKey(key: String) {
        mEditor?.remove(key)
        mEditor?.commit()
    }

}