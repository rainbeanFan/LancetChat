package cn.framework.base

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class BasePageAdapter(private val views: MutableList<View>) : PagerAdapter() {

    override fun getCount() = views.size

    override fun isViewFromObject(view: View, any: Any) = views === any

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        (container as ViewPager).addView(views[position])
        return views[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(views[position])
    }

}