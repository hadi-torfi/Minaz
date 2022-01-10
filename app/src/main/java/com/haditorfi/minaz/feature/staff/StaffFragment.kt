package com.haditorfi.minaz.feature.staff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.databinding.StaffFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffFragment : BaseFragment<StaffFragmentBinding>(), IPopup<Staff> {
    private val viewModel: StaffViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.toolbarBtn.setOnClickListener {
            goToAddOrEditFromIPopup(Staff())
        }

        viewModel.staffsLiveData.observe(viewLifecycleOwner) {
            val viewAdapter =
                StaffAdapter(requireContext(), it, itemClickListener = { item, personnel ->
                    popUp(item, personnel)
                })

            binding.rvPersonnel.run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.staff_list)
                toolbarBtn.text = getString(R.string.staff_new)
                toolbarBackBtn.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): StaffFragmentBinding =
        StaffFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: Staff) {
        viewModel.deleteStaff(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: Staff, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            StaffFragmentDirections.actionStaffToAddStaff(
                myClass
            )
        findNavController().navigate(action)
    }
}