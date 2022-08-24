package cn.framework.util

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

class AnimUtils {

    companion object {

        fun rotation(view: View): ObjectAnimator {
            return ObjectAnimator.ofFloat(view, "rotation", 0f, 360f).apply {
                duration = 2000
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
            }
        }

    }

}