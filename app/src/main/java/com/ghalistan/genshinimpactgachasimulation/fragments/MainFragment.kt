package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghalistan.genshinimpactgachasimulation.adapters.MainAdapter
import com.ghalistan.genshinimpactgachasimulation.viewModels.MainViewModel
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcBanner.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.banners.observe(viewLifecycleOwner) { bannerData ->
            binding.progressBar.visibility = View.VISIBLE
            binding.rcBanner.apply {
                adapter = MainAdapter(bannerData)
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