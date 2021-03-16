package com.cathay.homework.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cathay.homework.R
import com.cathay.homework.model.api.model.response.PlantItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter(
    private val detailFuncListener: DetailFuncListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var plantItems = arrayListOf<PlantItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PlantViewHolder

        val item = plantItems[position]

        Glide.with(holder.plantImg.context)
            .load(item.pic01Url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.drawable.ic_picture_small_empty)
            .error(R.drawable.ic_picture_small_empty)
            .into(holder.plantImg)

        holder.plantTitle.text = item.nameCh
        holder.plantDesc.text = item.alsoKnown

        holder.plantLayout.setOnClickListener {
            detailFuncListener.onPlantItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return plantItems.size
    }

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var plantImg: ImageView = itemView.iv_plant
        var plantTitle: TextView = itemView.tv_plant_title
        var plantDesc: TextView = itemView.tv_plant_desc
        var plantLayout: ConstraintLayout = itemView.layout_item_plant
    }

    fun updateData(data: ArrayList<PlantItem>) {
        plantItems = data
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }
}