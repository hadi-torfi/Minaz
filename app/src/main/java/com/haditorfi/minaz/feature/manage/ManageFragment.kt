package com.haditorfi.minaz.feature.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment

class ManageFragment : MyFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manage_fragment, container, false)
    }

}