package com.diekleinemietkiste.app.ui.favoriten

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.diekleinemietkiste.app.databinding.FragmentFavoritenBinding

class FavoritenFragment : Fragment() {
    private var _binding: FragmentFavoritenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.favoritBett1.setOnClickListener {
            Log.d("FavoritenFragmen", "Item Kinderbett Classic was clicked")
            val action =
                FavoritenFragmentDirections.actionNavigationFavoritenToProduktDetailFragment("Kinderbett Classic")
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
