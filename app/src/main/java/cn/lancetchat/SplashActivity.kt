package cn.lancetchat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import cn.framework.entity.Constants
import cn.framework.util.SPUtils
import cn.lancetchat.databinding.ActivityMainBinding

const val SKIP_MAIN = 0X00001

class SplashActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null

    private val mHandler = Handler { msg ->
        when (msg.what) {
            SKIP_MAIN -> {
                goMain()
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        mHandler.sendEmptyMessageDelayed(SKIP_MAIN, 2000)
    }

    private fun goMain() {
        val intent = Intent()
        val isFirst = SPUtils.getInstance()?.getBoolean(Constants.SP_IS_FIRST_OPEN, true)
        if (isFirst == true) {
            intent.setClass(this, MainActivity::class.java)
            SPUtils?.getInstance()?.putBoolean(Constants.SP_IS_FIRST_OPEN, false)
        } else {
            val token = SPUtils.getInstance()?.getString(Constants.SP_TOKEN, "")
            if (token.isNullOrEmpty()) {
                intent.setClass(this, MainActivity::class.java)
            } else {
                intent.setClass(this, MainActivity::class.java)
            }
        }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeMessages(SKIP_MAIN)
    }


}