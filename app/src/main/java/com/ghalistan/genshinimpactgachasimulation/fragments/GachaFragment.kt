package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghalistan.genshinimpactgachasimulation.adapters.GachaAdapter
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentGachaBinding
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.viewModels.GachaViewModel

class GachaFragment : Fragment() {
    private var _binding: FragmentGachaBinding? = null
    private val binding: FragmentGachaBinding
        get() = _binding!!

    private lateinit var itemData: List<PullableModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGachaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: GachaFragmentArgs by navArgs()
        itemData = safeArgs.pullableData.toList()
        val gachaViewModel = ViewModelProvider(this).get(GachaViewModel::class.java)
        gachaViewModel.generateItemOnlyList(itemData)
        gachaViewModel.doGachaProcess(safeArgs.onePull)
        gachaViewModel.pullResult.observe(viewLifecycleOwner, { data ->
            binding.rvCharacterResult.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = GachaAdapter(data)
            }
        })

        binding.backToHome.setOnClickListener {
            findNavController().navigate(GachaFragmentDirections.goBackHome())
        }
    }
}