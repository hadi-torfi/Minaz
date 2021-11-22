package com.haditorfi.minaz.feature.personnel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.databinding.PersonnelAddFragmentBinding


class AddPersonnelFragment : Fragment() {
    lateinit var binding: PersonnelAddFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonnelAddFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.personnel_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

}