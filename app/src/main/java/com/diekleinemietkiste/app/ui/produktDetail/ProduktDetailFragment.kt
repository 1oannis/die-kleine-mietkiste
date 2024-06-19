package com.diekleinemietkiste.app.ui.produktDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.diekleinemietkiste.app.databinding.FragmentProduktDetailBinding

class ProduktDetailFragment : Fragment() {
    private var _binding: FragmentProduktDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProduktDetailFragmentArgs by navArgs()
    private lateinit var viewModel: ProduktDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProduktDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.produktDetailName.text = args.produktName

        val produktbeschreibungView: TextView = binding.detailProduktbeschreibung
        binding.detailProduktbeschreibungTitle.setOnClickListener {
            produktbeschreibungView.visibility =
                if (produktbeschreibungView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
