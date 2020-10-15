package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghalistan.genshinimpactgachasimulation.adapters.ProfileAdapter
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentProfileBinding
import com.ghalistan.genshinimpactgachasimulation.viewModels.ProfileViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.user.observe(viewLifecycleOwner, { user ->
            binding.tvUsername.text = user[0].username
            binding.tvPrimogem.text = user[0].primogem.toString()
        })

        profileViewModel.item.observe(viewLifecycleOwner, { item ->
            binding.rvItemOwned.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = ProfileAdapter(item)
            }
        })
    }
}