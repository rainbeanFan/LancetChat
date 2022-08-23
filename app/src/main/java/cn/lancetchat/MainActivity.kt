package cn.lancetchat

import android.os.Bundle
import cn.framework.base.BaseUIActivity
import cn.lancetchat.databinding.ActivityMainBinding

class MainActivity : BaseUIActivity() {

    private var mBinding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)


    }
}