package com.diekleinemietkiste.app.ui.account

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        accountViewModel.accountName.observe(viewLifecycleOwner) {
            binding.halloKunde.text = it
        }

        accountViewModel.mieteCount.observe(viewLifecycleOwner) {
            binding.countCircleMiete.text = it.toString()
        }

        binding.groupFaqConstraintLayout.setOnClickListener {
            Log.d("AccountFragment", "FAQ button was clicked")
            findNavController().navigate(R.id.action_navigation_account_to_faqFragment)
        }

        binding.groupMieteConstraintLayout.setOnClickListener {
            Log.d("AccountFragment", "Mieten button was clicked")
        }

        binding.groupFavoritenConstraintLayout.setOnClickListener {
            Log.d("AccountFragment", "Favoriten button was clicked")
            findNavController().navigate(R.id.action_navigation_account_to_navigation_favoriten)
        }

        binding.groupMeineMieten.setOnClickListener {
            Log.d("AccountFragment", "Meine Mieten subsection button was clicked")
        }

        binding.groupMeineDaten.setOnClickListener {
            Log.d("AccountFragment", "Meine Daten subsection button was clicked")
        }

        binding.groupBewertungen.setOnClickListener {
            Log.d("AccountFragment", "Meine Bewertungen subsection button was clicked")
        }

        binding.groupReklamationen.setOnClickListener {
            Log.d("AccountFragment", "Meine Reklamationen subsection button was clicked")
        }

        binding.groupEinstellungen.setOnClickListener {
            Log.d("AccountFragment", "Einstellungen subsection button was clicked")
        }

        binding.groupHilfe.setOnClickListener {
            Log.d("AccountFragment", "Hilfe subsection button was clicked")
            findNavController().navigate(R.id.action_navigation_account_to_faqFragment)
        }

        binding.impressumAgb.setOnClickListener {
            Log.d("AccountFragment", "Impressum AGB button was clicked")
        }

        binding.impressumDatenschutz.setOnClickListener {
            Log.d("AccountFragment", "Impressum Datenschutz button was clicked")
        }

        binding.impressumImpressum.setOnClickListener {
            Log.d("AccountFragment", "Impressum button was clicked")
        }

        binding.impressumTeilnahmebedingungen.setOnClickListener {
            Log.d("AccountFragment", "Impressum Teilnahmebedingungen button was clicked")
        }

        return root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        underlineTextView(view.findViewById(R.id.impressum_agb), getString(R.string.impressum_agb))
        underlineTextView(
            view.findViewById(R.id.impressum_datenschutz),
            getString(R.string.impressum_datenschutz),
        )
        underlineTextView(
            view.findViewById(R.id.impressum_impressum),
            getString(R.string.impressum_impressum),
        )
        underlineTextView(
            view.findViewById(R.id.impressum_teilnahmebedingungen),
            getString(R.string.impressum_teilnahmebedingungen),
        )
    }

    private fun underlineTextView(
        textView: TextView,
        text: String,
    ) {
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, text.length, 0)
        textView.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
