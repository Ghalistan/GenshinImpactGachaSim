package com.ghalistan.genshinimpactgachasimulation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghalistan.genshinimpactgachasimulation.fragments.MainFragmentDirections
import com.ghalistan.genshinimpactgachasimulation.models.BannerModel
import com.ghalistan.genshinimpactgachasimulation.databinding.ItemBannerLayoutBinding
import kotlinx.android.synthetic.main.item_banner_layout.view.*

class MainAdapter(private val banners: List<BannerModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: ItemBannerLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(bannerData: BannerModel) {
            itemView.tv_banner_title.text = bannerData.name

            itemView.cardBody.setOnClickListener{
                val action = MainFragmentDirections.moveToDetailBannerFragment(bannerData.name)
                itemView.findNavController().navigate(action)
            }

            Glide.with(itemView)
                .load(bannerData.bannerPict)
                .into(itemView.iv_banner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemBannerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val bannerData = banners[position]
        holder.bind(bannerData)
    }

    override fun getItemCount(): Int = banners.size
}