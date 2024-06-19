package com.diekleinemietkiste.app.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diekleinemietkiste.app.adapter.CustomNestedExpandableListAdapter
import com.diekleinemietkiste.app.databinding.FragmentFaqBinding
import com.diekleinemietkiste.app.dataclass.ChildItem
import com.diekleinemietkiste.app.dataclass.ParentItem
import com.diekleinemietkiste.app.dataclass.SubChildItem

class FaqFragment : Fragment() {
    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialisierung der ExpandableListView
        val expandableListView = binding.faqExpandableList
        val adapter = CustomNestedExpandableListAdapter(requireContext(), prepareData())
        expandableListView.setAdapter(adapter)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareData(): List<ParentItem> {
        // Mock daten für die FAQ Seite
        return listOf(
            ParentItem(
                "Bestellungen & Zahlungen",
                listOf(
                    ChildItem(
                        "Bestellen",
                        listOf(SubChildItem("Wie kann ich eine Bestellung aufgeben?")),
                    ),
                    ChildItem(
                        "Zahlungsmethoden",
                        listOf(
                            SubChildItem("Welche Zahlungsmethoden gibt es?"),
                            SubChildItem("Warum wird meine gewünschte Zahlungsmethode nicht angezeigt?"),
                        ),
                    ),
                    ChildItem("Zahlung", listOf(SubChildItem("Wann ist meine Zahlung fällig?"))),
                    ChildItem(
                        "Rückerstattung & Storno",
                        listOf(SubChildItem("Wie kann ich eine Miete stornieren?")),
                    ),
                ),
            ),
            ParentItem("Versand & Lieferungen", emptyList()),
            ParentItem("Sendungsverfolgung", emptyList()),
            ParentItem("Auf- & Abbauservice", emptyList()),
        )
    }
}
