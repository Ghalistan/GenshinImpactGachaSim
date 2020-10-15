package com.ghalistan.genshinimpactgachasimulation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghalistan.genshinimpactgachasimulation.databinding.ItemOwnedLayoutBinding
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import kotlinx.android.synthetic.main.item_owned_layout.view.*

class ProfileAdapter(private val itemOwned: List<ItemModel>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    class ProfileViewHolder(itemView: ItemOwnedLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: ItemModel) {
            itemView.characterPict.load(item.itemPict)

            itemView.characterName.text = item.name
            itemView.rb_character_rarity.apply {
                numStars = item.rarity
                rating = item.rarity.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view =
            ItemOwnedLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item = itemOwned[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemOwned.size
}