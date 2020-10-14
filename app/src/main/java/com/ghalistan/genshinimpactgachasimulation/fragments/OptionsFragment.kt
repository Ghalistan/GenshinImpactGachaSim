package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
    private var _binding: FragmentOptionsBinding? = null
    private val binding: FragmentOptionsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }
}