package com.haditorfi.minaz.feature.personnel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.*
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.databinding.PersonnelAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddPersonnelFragment : Fragment() {
    lateinit var binding: PersonnelAddFragmentBinding
    private val args by navArgs<AddPersonnelFragmentArgs>()
    private val viewModel: PersonnelViewModel by viewModel()
    private var errorMessage = ""
    private var role = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonnelAddFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activeEditMode()
            edtPersonnel = args.personnelData

            include.toolbarBtn.setOnClickListener {
                save()
            }
        }
    }

    private fun PersonnelAddFragmentBinding.save() {
        val name = edtPersonnelName.text.toString()
        val mobile = edtPersonnelMobile.text.toString()
        val address = edtPersonnelAddress.text.toString()

        when (radioGroup.checkedRadioButtonId) {
            R.id.radioPersonnel -> role = PERSONNEL
            R.id.radioManager -> role = MANAGER
            R.id.radioSecretary -> role = SECRETARY
        }

        if (validate(name, mobile)) {
            val personnel = Personnel(args.personnelData.id, name, mobile, address, role)

            if (args.personnelData.activeEditMode)
                viewModel.updatePersonnel(personnel)
            else
                viewModel.insertPersonnel(personnel)

            root.toast(getString(R.string.success))
            findNavController().navigateUp()
        } else {
            root.toast(errorMessage)
        }
    }

    private fun validate(name: String, mobile: String): Boolean {
        return if (name.isEmpty())
            manageValidate(getString(R.string.name_require))
        else if (mobile.isEmpty())
            manageValidate(getString(R.string.mobil_require))
        else if (!mobile.isPhone())
            manageValidate(getString(R.string.isPhone_message))
        else
            true
    }

    private fun manageValidate(errorMessage: String): Boolean {
        this.errorMessage = errorMessage
        binding.root.hideKeyboard()
        return false
    }

    private fun PersonnelAddFragmentBinding.activeEditMode() {
        if (args.personnelData.activeEditMode) {
            include.toolbarTitleTv.text = getString(R.string.edit)
            include.toolbarBtn.text = getString(R.string.edit)
        }
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.personnel_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}