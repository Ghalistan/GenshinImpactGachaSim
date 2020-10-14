package com.ghalistan.genshinimpactgachasimulation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.databinding.ItemCharacterInfoBinding
import kotlinx.android.synthetic.main.item_character_info.view.*

class DetailBannerAdapter(private val pullableData : List<PullableModel>) : RecyclerView.Adapter<DetailBannerAdapter.DetailBannerViewHolder>() {
    class DetailBannerViewHolder(itemView: ItemCharacterInfoBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(pullData : PullableModel) {
            val item = pullData.pullableObject
            Glide.with(itemView.context)
                .load(item.itemPict)
                .into(itemView.characterPict)

            itemView.characterName.text = item.name
            itemView.rb_character_rarity.apply {
                numStars = item.rarity
                rating = item.rarity.toFloat()
            }

            itemView.tv_rate_up.visibility = if (pullData.featuredItem) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBannerViewHolder {
        val view = ItemCharacterInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailBannerViewHolder, position: Int) {
        val pullData = pullableData[position]
        holder.bind(pullData)
    }

    override fun getItemCount(): Int = pullableData.size
}