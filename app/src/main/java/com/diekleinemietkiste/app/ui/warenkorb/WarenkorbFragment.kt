package com.diekleinemietkiste.app.ui.warenkorb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.diekleinemietkiste.app.databinding.FragmentWarenkorbBinding

class WarenkorbFragment : Fragment() {
    private var _binding: FragmentWarenkorbBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWarenkorbBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.warenkorbItem.setOnClickListener {
            val action =
                WarenkorbFragmentDirections.actionNavigationWarenkorbToProduktDetailFragment("Kinderbett Classic")
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
