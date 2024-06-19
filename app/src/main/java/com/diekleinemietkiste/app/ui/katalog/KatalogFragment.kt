package com.diekleinemietkiste.app.ui.katalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.adapter.CatalogAdapter
import com.diekleinemietkiste.app.databinding.FragmentKatalogBinding

class KatalogFragment : Fragment() {
    private var _binding: FragmentKatalogBinding? = null
    private val binding get() = _binding!!

    private var produktartId: Int = 0
    private var kategorieName: String = ""
    private var produktartName: String = ""

    private val args: KatalogFragmentArgs by navArgs()
    private val katalogViewModel: KatalogViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var catalogAdapter: CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentKatalogBinding.inflate(inflater, container, false)
        recyclerView = binding.katalogRecyclerView
        setupRecyclerView()
        return binding.root
    }

    /**
     * Initialisierung einer Katalog Ansicht mithilfe der Custom Implementierung der RecyclerView
     */
    private fun setupRecyclerView() {
        catalogAdapter =
            CatalogAdapter { produkt ->
                Log.d("KatalogFragment", "Produkt ${produkt.bezeichnung} was clicked")
                val action =
                    KatalogFragmentDirections.actionKatalogFragmentToProduktDetailFragment(produkt.bezeichnung)
                findNavController().navigate(action)
            }
        recyclerView.adapter = catalogAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        produktartId = args.produktartId
        kategorieName = args.kategorieName
        produktartName = args.produktartName
        val fragmentLabel = "$kategorieName > $produktartName"
        (activity as? AppCompatActivity)?.supportActionBar?.title = fragmentLabel
        katalogViewModel.getProduktListByProduktId(produktartId)
            .observe(viewLifecycleOwner) { produktList ->
                catalogAdapter.submitList(produktList)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bread Crumps zuruecksetzen
        (activity as? AppCompatActivity)?.supportActionBar?.title =
            getString(R.string.katalog_title)
    }
}
