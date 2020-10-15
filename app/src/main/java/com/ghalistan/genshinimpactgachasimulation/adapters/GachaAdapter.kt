package com.ghalistan.genshinimpactgachasimulation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghalistan.genshinimpactgachasimulation.databinding.ItemCharacterInfoBinding
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import kotlinx.android.synthetic.main.item_character_info.view.*

class GachaAdapter(private val itemData: List<ItemModel>) :
    RecyclerView.Adapter<GachaAdapter.GachaViewHolder>() {
    class GachaViewHolder(itemView: ItemCharacterInfoBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(data: ItemModel) {
            itemView.characterPict.load(data.itemPict)

            itemView.characterName.text = data.name
            itemView.rb_character_rarity.apply {
                numStars = data.rarity
                rating = data.rarity.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GachaViewHolder {
        val view =
            ItemCharacterInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GachaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GachaViewHolder, position: Int) {
        val data = itemData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = itemData.size
}