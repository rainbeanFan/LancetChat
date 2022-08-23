package cn.lancetchat

import android.os.Bundle
import cn.framework.base.BaseUIActivity
import cn.lancetchat.databinding.ActivityLoginBinding

class LoginActivity : BaseUIActivity() {

    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

}