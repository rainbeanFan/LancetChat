package cn.lancetchat

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import cn.framework.base.BaseUIActivity
import cn.framework.util.LogUtils
import cn.lancetchat.adapter.GuideAdapter
import cn.lancetchat.databinding.ActivityGuideBinding

class GuideActivity : BaseUIActivity() {

    private var mBinding: ActivityGuideBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        initView()
    }

    private fun initView() {

        mBinding!!.tvGuideSkip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        mBinding!!.viewPager.apply {
            adapter = GuideAdapter()
        }.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectPosition(position)
            }
        })


    }


    private fun selectPosition(position: Int) {
        when (position) {
            0 -> {
                mBinding!!.ivGuidePoint1.setImageResource(R.drawable.img_guide_point_p)
                mBinding!!.ivGuidePoint2.setImageResource(R.drawable.img_guide_point)
                mBinding!!.ivGuidePoint3.setImageResource(R.drawable.img_guide_point)
            }
            1 -> {
                mBinding!!.ivGuidePoint1.setImageResource(R.drawable.img_guide_point)
                mBinding!!.ivGuidePoint2.setImageResource(R.drawable.img_guide_point_p)
                mBinding!!.ivGuidePoint3.setImageResource(R.drawable.img_guide_point)
            }
            2 -> {
                mBinding!!.ivGuidePoint1.setImageResource(R.drawable.img_guide_point)
                mBinding!!.ivGuidePoint2.setImageResource(R.drawable.img_guide_point)
                mBinding!!.ivGuidePoint3.setImageResource(R.drawable.img_guide_point_p)
            }
        }
    }


}