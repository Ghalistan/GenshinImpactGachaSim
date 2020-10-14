package com.ghalistan.genshinimpactgachasimulation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghalistan.genshinimpactgachasimulation.R
import com.ghalistan.genshinimpactgachasimulation.adapters.GachaAdapter
import com.ghalistan.genshinimpactgachasimulation.databinding.FragmentGachaBinding
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
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
        val gachaViewModel = ViewModelProvider(this, GachaViewModel.FACTORY(safeArgs.pullableData.toList())).get(GachaViewModel::class.java)
        gachaViewModel.doGachaProcess(safeArgs.onePull)
        gachaViewModel.pullResult.observe(viewLifecycleOwner, { data ->
            binding.rvCharacterResult.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = GachaAdapter(data)
            }
        })
    }
}