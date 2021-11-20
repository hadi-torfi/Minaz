package com.haditorfi.minaz.feature.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.databinding.ServiceAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddServiceFragment : Fragment() {
    private lateinit var binding: ServiceAddFragmentBinding
    private val viewModel: ServiceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceAddFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}