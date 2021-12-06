package com.haditorfi.minaz.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import com.haditorfi.minaz.databinding.ReportFragmentBinding

class ReportFragment : MyFragment() {
    lateinit var binding: ReportFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReportFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }
    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.report)
        }
    }
}