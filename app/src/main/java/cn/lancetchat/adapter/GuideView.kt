package cn.lancetchat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.lancetchat.R

class GuideView(
    layoutInflater: LayoutInflater,
    container: ViewGroup?
) {

    val view: View = layoutInflater
        .inflate(R.layout.layout_guide_one, container, false)

    private var ivPic: ImageView = view.findViewById(R.id.iv_logo)
    private var tvSlogan: TextView = view.findViewById(R.id.tv_slogan)

    fun bind(position: Int) {
        when (position) {
            0 -> {
                ivPic.setImageResource(R.drawable.guide_star_anim)
                tvSlogan.text = view.context.getString(R.string.text_guide_pager_1)
            }
            1 -> {
                ivPic.setImageResource(R.drawable.guide_night_anim)
                tvSlogan.text = view.context.getString(R.string.text_guide_pager_2)
            }
            2 -> {
                ivPic.setImageResource(R.drawable.guide_smile_anim)
                tvSlogan.text = view.context.getString(R.string.text_guide_pager_3)
            }
        }
    }

}