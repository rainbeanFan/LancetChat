package cn.lancetchat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GuideAdapter : RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        return GuideViewHolder(GuideView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = 3

    class GuideViewHolder internal constructor(private val guideView: GuideView) :
        RecyclerView.ViewHolder(guideView.view) {
        internal fun bind(position: Int) {
            guideView.bind(position)
        }
    }

}