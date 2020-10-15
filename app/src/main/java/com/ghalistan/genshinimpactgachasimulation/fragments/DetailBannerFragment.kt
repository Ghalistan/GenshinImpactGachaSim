package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ghalistan.genshinimpactgachasimulation.adapters.DetailBannerAdapter
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentDetailBannerBinding
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.viewModels.DetailBannerViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class DetailBannerFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentDetailBannerBinding? = null
    private val binding
        get() = _binding!!

    private var itemPullable = listOf<PullableModel>()
    private lateinit var bannerName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: DetailBannerFragmentArgs by navArgs()
        bannerName = safeArgs.bannerName
        requireActivity().actualAppBar.title = bannerName

        binding.progressBar.visibility = View.VISIBLE
        binding.detailBannerContainer.visibility = View.GONE

        val detailViewModel =
            ViewModelProvider(this, DetailBannerViewModel.FACTORY(bannerName)).get(
                DetailBannerViewModel::class.java
            )

        detailViewModel.getPullables().observe(viewLifecycleOwner, { pullableData ->
            itemPullable = detailViewModel.shortItem(pullableData)
            binding.rvFiveStarChar.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = DetailBannerAdapter(itemPullable)
            }

            binding.btnSinglePull.setOnClickListener(this)
            binding.btnTenPull.setOnClickListener(this)
        })

        detailViewModel.getBannerData().observe(viewLifecycleOwner, { bannerData ->
            binding.tvBannerTitle.text = bannerData.name

            binding.ivBanner.load(bannerData.bannerPict)

            binding.detailBannerContainer.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })
    }

    override fun onClick(v: View) {
        var pullAmount = 0
        var costAmount = 0
        var onePull = true

        when (v.id) {
            binding.btnSinglePull.id -> {
                pullAmount = 1
                costAmount = 160
            }
            binding.btnTenPull.id -> {
                pullAmount = 10
                costAmount = 1600
                onePull = false
            }
            else -> Unit
        }

        val action = DetailBannerFragmentDirections.doGacha(itemPullable.toTypedArray(), onePull)

        MaterialAlertDialogBuilder(v.context)
            .setTitle("${pullAmount}x Pull on ${bannerName}")
            .setMessage("Are you sure u want to do gacha on this banner? This will cost you $costAmount Primogem")
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { _, _ ->
                v.findNavController().navigate(action)
            }
            .show()
    }
}