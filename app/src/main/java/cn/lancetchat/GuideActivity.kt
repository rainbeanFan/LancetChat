package cn.lancetchat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import cn.framework.base.BasePageAdapter
import cn.framework.base.BaseUIActivity
import cn.lancetchat.databinding.ActivityGuideBinding

class GuideActivity : BaseUIActivity() {

    private var mBinding: ActivityGuideBinding? = null

    private var view1: View? = null
    private var view2: View? = null
    private var view3: View? = null

    private var mViews = mutableListOf<View>()
    private var mAdapter: BasePageAdapter? = null

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

        view1 = View.inflate(this, R.layout.layout_guide_one, null)
        view2 = View.inflate(this, R.layout.layout_guide_two, null)
        view3 = View.inflate(this, R.layout.layout_guide_three, null)

        mViews.add(view1!!)
        mViews.add(view2!!)
        mViews.add(view3!!)

        mAdapter = BasePageAdapter(mViews)
        mBinding!!.viewPager.apply {
            offscreenPageLimit = mViews.size
            adapter = mAdapter
        }.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                selectPosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

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