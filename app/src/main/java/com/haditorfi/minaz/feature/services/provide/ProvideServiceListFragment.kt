package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.common.visible
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ProvideServiceListFragment : BaseFragment<ServiceProvideListFragmentBinding>(),
    IPopup<ProvideService> {
    private val viewModel: ProvideServiceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allProvideService.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createProvideService()
                val serviceViewAdapter =
                    ProvideServiceAdapter(
                        requireContext(),
                        it
                    ) { item, provides ->
                        when (item.id) {
                            R.id.item_service_layout -> goToDetailFragment(provides as Provides)
                            R.id.btn_more -> popUp(
                                requireContext(),
                                item,
                                provides as ProvideService
                            )
                        }
                    }
                rvProvideService.run {
                    setHasFixedSize(true)
                    adapter = serviceViewAdapter
                }
            }

            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(ProvideService())
            }
        }
    }

    private fun goToDetailFragment(provideService: Provides) {
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToDetailProvideService(
                provideService
            )
        findNavController().navigate(action)
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.service_provided)
                toolbarBtn.visible()
                toolbarBtn.text = getString(R.string.service_provide)
            }
        }
    }

    private fun createProvideService() {
        val s1 = Service(1, "شمع صورت", "80000")
        val s2 = Service(2, "اپیلاسیون تمام بدن", "180000")
        val s3 = Service(3, "اصلاح ابرو", "32000")
        val s4 = Service(4, "کوتاهی مو", "86000")
        val s5 = Service(5, "شنیون", "1800000")
        val s6 = Service(6, "تاتو", "850000")
        val s7 = Service(7, "کاشت ناخن", "250000")
        val s8 = Service(8, "رنگ مو", "350000")

        val p1 =
            ProvideService(
                1,
                2,
                listOf(s3, s2, s4, s1, s8, s6, s5, s7),
                Date(),
                "20000",
                "توضیحات"
            )
        val p2 =
            ProvideService(1, 3, listOf(s4, s1, s3, s2, s4, s5, s7, s8), Date(), "50000", "توضیح")
        viewModel.insertProvideService(p1)
        viewModel.insertProvideService(p2)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideListFragmentBinding =
        ServiceProvideListFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: ProvideService) {
        viewModel.deleteProvideService(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: ProvideService, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            ProvideServiceListFragmentDirections.actionProvideServiceListToAddProvideService(
                myClass
            )
        findNavController().navigate(action)
    }
}