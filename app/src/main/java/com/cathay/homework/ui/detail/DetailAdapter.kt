package com.cathay.homework.ui.detail

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cathay.homework.R
import com.cathay.homework.model.api.model.response.PlantItem
import com.cathay.homework.model.api.model.response.ZooItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.item_plants.view.*

class DetailAdapter(
        private val item: ZooItem,
        private val detailFuncListener: DetailFuncListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_DETAIL = 0
        const val VIEW_TYPE_PLANT_TITLE = 1
        const val VIEW_TYPE_PLANTS = 2
    }

    private var plantAdapter: PlantAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DETAIL -> {
                val mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_detail, parent, false)
                DetailViewHolder(mView)
            }
            VIEW_TYPE_PLANT_TITLE -> {
                val mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_plant_title, parent, false)
                PlantTitleViewHolder(mView)
            }
            else -> {
                val mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_plants, parent, false)
                PlantsViewHolder(mView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_DETAIL
            1 -> VIEW_TYPE_PLANT_TITLE
            else -> VIEW_TYPE_PLANTS
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DetailViewHolder -> {
                Glide.with(holder.categoryImg.context)
                    .load(item.picUrl)
                    .placeholder(R.drawable.ic_picture_small_empty)
                    .error(R.drawable.ic_picture_small_empty)
                    .into(holder.categoryImg)

                holder.categoryInfo.text = item.info
                holder.categoryArea.text = item.category

                if (TextUtils.isEmpty(item.memo)) {
                    holder.categoryMemo.visibility = View.GONE
                } else {
                    holder.categoryMemo.text = item.memo
                }

                holder.categoryOpenWeb.setOnClickListener {
                    detailFuncListener.onOpenWeb(item.url)
                }
            }
            is PlantsViewHolder -> {
                plantAdapter = PlantAdapter(detailFuncListener)
                holder.plantRecyclerView.also {
                    it.setHasFixedSize(true)
                    it.adapter = plantAdapter
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImg: ImageView = itemView.iv_detail
        var categoryInfo: TextView = itemView.tv_info
        var categoryMemo: TextView = itemView.tv_memo
        var categoryArea: TextView = itemView.tv_area
        var categoryOpenWeb: TextView = itemView.tv_open_web
    }

    class PlantTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class PlantsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var plantRecyclerView: RecyclerView = itemView.rv_plant
    }

    fun updatePlantsData(data: ArrayList<PlantItem>) {
        plantAdapter?.updateData(data)
    }

    fun isPlantsEmpty(): Boolean? {
        return plantAdapter?.isEmpty()
    }
}