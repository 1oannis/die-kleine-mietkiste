package com.diekleinemietkiste.app.ui.startseite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.databinding.FragmentStartseiteBinding

class StartseiteFragment : Fragment() {
    private var _binding: FragmentStartseiteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartseiteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.groupSoFunktionierts.setOnClickListener {
            Log.d("StartseiteFragment", "So funktioniert's button was clicked")
            findNavController().navigate(R.id.action_navigation_startseite_to_hilfeseitenFragment)
        }

        binding.groupUnsereProduktKategorien.setOnClickListener {
            Log.d("StartseiteFragment", "Unsere Produktkategorien button was clicked")
            findNavController().navigate(R.id.action_navigation_startseite_to_navigation_suchen)
        }

        binding.groupUnsereAusstattungspakete.setOnClickListener {
            Log.d("StartseiteFragment", "Unsere Ausstattungspakete button was clicked")
            findNavController().navigate(R.id.action_navigation_startseite_to_ausstattungspaketeFragment)
        }

        binding.groupUnsereAngebotskiste.setOnClickListener {
            Log.d("StartseiteFragment", "Unsere Angebotskiste button was clicked")
        }

        binding.groupHilfeUndSupport.setOnClickListener {
            Log.d("StartseiteFragment", "Hilfe und Support button was clicked")
            findNavController().navigate(R.id.action_navigation_startseite_to_faqFragment)
        }

        binding.imageAngeboteFuerDich.setOnClickListener {
            Log.d("StartseiteFragment", "Angebot button was clicked")
            val action =
                StartseiteFragmentDirections.actionNavigationStartseiteToProduktDetailFragment("Kinderbett Classic")
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
