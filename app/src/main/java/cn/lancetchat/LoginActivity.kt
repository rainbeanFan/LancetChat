package cn.lancetchat

import android.os.Bundle
import cn.framework.base.BaseUIActivity
import cn.framework.util.LogUtils
import cn.lancetchat.databinding.ActivityLoginBinding
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class LoginActivity : BaseUIActivity() {

    private lateinit var mBinding: ActivityLoginBinding

    private var mDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()

    }

    private fun initView() {
        mBinding.btnSendCode.setOnClickListener{
            countDown()
        }
    }

    private fun countDown(){
        mDisposable = Flowable.intervalRange(0,60,0,1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                LogUtils.i("$it")
                mBinding.btnSendCode.isEnabled = false
                mBinding.btnSendCode.text = "${60-it}s"
            }.doOnComplete {
                mBinding.btnSendCode.isEnabled = true
                mBinding.btnSendCode.text = "发送"

            }.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }

}