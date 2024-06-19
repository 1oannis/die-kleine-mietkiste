package com.diekleinemietkiste.app.ui.suchen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diekleinemietkiste.app.adapter.CustomExpandableListAdapter
import com.diekleinemietkiste.app.databinding.FragmentSuchenBinding
import com.diekleinemietkiste.app.model.Produktart
import kotlinx.coroutines.launch

class SuchenFragment : Fragment() {
    private val logName = "SuchenFragment"
    private var _binding: FragmentSuchenBinding? = null
    private val binding get() = _binding!!
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: CustomExpandableListAdapter
    private lateinit var viewModel: SuchenViewModel
    private var lastExpandedPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSuchenBinding.inflate(inflater, container, false)
        expandableListView = binding.expandableListView

        // Weiterleitung zur Detailansichteim anclicken eines Produktes
        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val selectedProduktart =
                (expandableListView.expandableListAdapter as CustomExpandableListAdapter).getChild(
                    groupPosition,
                    childPosition,
                ) as Produktart
            Log.d(logName, selectedProduktart.toString())
            lifecycleScope.launch {
                val kategorieName = viewModel.getKategorieNameById(selectedProduktart.kategorieId)
                val action =
                    SuchenFragmentDirections.actionNavigationSuchenToKatalogFragment(
                        selectedProduktart.produktartId,
                        selectedProduktart.bezeichnung,
                        kategorieName,
                    )
                findNavController().navigate(action)
            }
            true
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SuchenViewModel::class.java]
        // Initialisierung der Suchliste mithilfe der custom Implementierung der ExpandableList
        viewModel.kategorien.observe(viewLifecycleOwner) { kategorieList ->
            if (kategorieList.isNotEmpty()) {
                lifecycleScope.launch {
                    val productsMap = viewModel.getProduktartenZuKategorieList(kategorieList)
                    Log.d("SuchenFragment", kategorieList.toString())
                    Log.d("SuchenFragment", productsMap.toString())
                    adapter =
                        CustomExpandableListAdapter(requireContext(), kategorieList, productsMap)
                    expandableListView.setAdapter(adapter)
                }
            } else {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.emptyStateText.text = "No categories found. Please add some!"
            }
        }
        expandableListView.setOnGroupExpandListener { groupPosition ->
            if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                expandableListView.collapseGroup(lastExpandedPosition)
            }
            lastExpandedPosition = groupPosition
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
