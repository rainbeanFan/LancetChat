package cn.framework.view

import android.animation.ObjectAnimator
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import cn.framework.R
import cn.framework.manager.DialogManager
import cn.framework.util.AnimUtils

class LoadingView {

    private var mLoadingView: DialogView
    private var mIvLoading: ImageView
    private var mTvLoadingTextView: TextView
    private var mAnim: ObjectAnimator

    constructor(context: Context) {
        mLoadingView = DialogManager.getInstance().initView(context, R.layout.dialog_loading)
        mIvLoading = mLoadingView.findViewById(R.id.iv_loading)
        mTvLoadingTextView = mLoadingView.findViewById(R.id.tv_loading_text)
        mAnim = AnimUtils.rotation(mIvLoading)
    }

    fun setLoadingText(text: String) {
        if (text.isNotBlank()) {
            mTvLoadingTextView.text = text
        }
    }

    fun show(text: String = "") {
        mAnim.start()
        if (text.isNotBlank()) {
            setLoadingText(text)
        }
        DialogManager.getInstance().show(mLoadingView)
    }

    fun hide() {
        mAnim.pause()
        DialogManager.getInstance().hide(mLoadingView)
    }

    fun setCancelable(flag: Boolean) = mLoadingView.setCancelable(flag)

}