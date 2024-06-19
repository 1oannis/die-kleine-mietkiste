package com.diekleinemietkiste.app.ui.ausstattungspakete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diekleinemietkiste.app.databinding.FragmentAusstattungspaketeBinding

class AusstattungspaketeFragment : Fragment() {
    private var _binding: FragmentAusstattungspaketeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAusstattungspaketeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
