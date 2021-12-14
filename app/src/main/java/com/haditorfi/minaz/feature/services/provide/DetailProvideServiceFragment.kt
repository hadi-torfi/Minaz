package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.databinding.ServiceProvideDetailFragmentBinding

class DetailProvideServiceFragment : Fragment() {
    private lateinit var binding: ServiceProvideDetailFragmentBinding
    private val args by navArgs<DetailProvideServiceFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceProvideDetailFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            data = args.data
        }
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service_list)
            include.toolbarBtn.visibility = View.INVISIBLE
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}