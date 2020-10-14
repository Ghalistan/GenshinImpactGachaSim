package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghalistan.genshinimpactgachasimulation.adapters.BannerAdapter
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentBannerBinding
import com.ghalistan.genshinimpactgachasimulation.viewModels.BannerViewModel

class BannerFragment : Fragment() {
    private var _binding: FragmentBannerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcBanner.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        val mainViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        mainViewModel.banners.observe(viewLifecycleOwner) { bannerData ->
            binding.progressBar.visibility = View.VISIBLE
            binding.rcBanner.apply {
                adapter = BannerAdapter(bannerData)
            }
        }

        binding.refreshLayout.setOnRefreshListener {
            mainViewModel.getBannerFromDB()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}