package com.ghalistan.genshinimpactgachasimulation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghalistan.genshinimpactgachasimulation.databinding.ItemCharacterInfoBinding
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import kotlinx.android.synthetic.main.item_character_info.view.*

class DetailBannerAdapter(private val pullableData: List<PullableModel>) :
    RecyclerView.Adapter<DetailBannerAdapter.DetailBannerViewHolder>() {
    private var rarity = 0

    class DetailBannerViewHolder(itemView: ItemCharacterInfoBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bind(pullData: PullableModel, rarity: Int): Int {
            return if (rarity != pullData.pullableObject.rarity) {
                itemView.rarity_context.apply {
                    visibility = View.VISIBLE
                    text = pullData.pullableObject.rarity.toString() + " STAR"
                }
                anotherBind(pullData)
                pullData.pullableObject.rarity
            } else {
                itemView.rarity_context.visibility = View.GONE
                anotherBind(pullData)
                rarity
            }
        }

        private fun anotherBind(pullData: PullableModel) {
            itemView.tv_rate_up.visibility = if (pullData.featuredItem) View.VISIBLE else View.GONE

            val item = pullData.pullableObject
            itemView.characterPict.load(item.itemPict)

            itemView.characterName.text = item.name
            itemView.rb_character_rarity.apply {
                numStars = item.rarity
                rating = item.rarity.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBannerViewHolder {
        val view =
            ItemCharacterInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailBannerViewHolder, position: Int) {
        val pullData = pullableData[position]
        rarity = holder.bind(pullData, rarity)
    }

    override fun getItemCount(): Int = pullableData.size
}