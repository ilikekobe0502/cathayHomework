package com.cathay.homework.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cathay.homework.R
import com.cathay.homework.model.api.model.response.ZooItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
    private val homeFuncListener: HomeFuncListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var zooItems = arrayListOf<ZooItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as HomeViewHolder

        val item = zooItems[position]

        Glide.with(holder.categoryImg.context)
            .load(item.picUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_picture_small_empty)
            .error(R.drawable.ic_picture_small_empty)
            .into(holder.categoryImg)

        holder.categoryTitle.text = item.name
        holder.categoryDesc.text = item.info
        holder.categoryMemo.text = item.memo

        holder.categoryLayout.setOnClickListener {
            homeFuncListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return zooItems.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImg: ImageView = itemView.iv_category
        var categoryTitle: TextView = itemView.tv_category_title
        var categoryDesc: TextView = itemView.tv_category_desc
        var categoryMemo: TextView = itemView.tv_category_memo
        var categoryLayout: ConstraintLayout = itemView.layout_item_category
    }

    fun updateData(data: ArrayList<ZooItem>) {
        zooItems = data
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }
}