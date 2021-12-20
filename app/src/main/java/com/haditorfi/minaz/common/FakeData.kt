package com.haditorfi.minaz.common

import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import com.haditorfi.minaz.feature.product.ProductViewModel
import com.haditorfi.minaz.feature.services.provide.ProvideServiceViewModel
import com.haditorfi.minaz.feature.services.service.ServiceViewModel
import com.haditorfi.minaz.feature.staff.StaffViewModel
import java.util.*

class FakeData(
    private val customerViewModel: CustomerViewModel,
    private val productViewModel: ProductViewModel,
    private val staffViewModel: StaffViewModel,
    private val serviceViewModel: ServiceViewModel,
    private val provideServiceViewModel: ProvideServiceViewModel,
) {
    fun createFakeData() {
        addCustomer()
        createProduct()
        createStaffAndRole()
        createServiceAndProvideService()
    }

    private fun addCustomer() {
        val c1 = Customer("سمانه محمدی", "09166424100", "شوش خیابان مدرس")
        val c2 = Customer("مهسا طاهری", "09352623050", "شوش خیابان ")
        val c3 = Customer("سارینا رحمتی", "09352623055", "شوش ")
        val c4 = Customer("پانته آ بهرام", "09352623066", "شوش چهارستگاه نبش دستغیب")
        customerViewModel.insertCustomer(c1, c2, c3,c4)
    }

    private fun createProduct() {
        val p1 = Product("کرم ووکس", "180000", "5")
        val p2 = Product("کرم ستاره", "30000", "6")
        val p3 = Product("کرم آبرسان آلوئه ورا", "120000", "10")
        productViewModel.insertProduct(p1, p2, p3)
    }

    private fun createStaffAndRole() {
        val p1 = Staff("مینا عبدالنبی", "09166424196", "تهران", MANAGER)
        val p2 = Staff("نازنین طرفی", "09352623050", "شوش", SECRETARY)
        val p3 = Staff("ساره بیات", "09352625553", "تهران", PERSONNEL)
        val p4 = Staff("مهناز افشار", "09352626532", "تهران", PERSONNEL)
        staffViewModel.insertStaff(p1, p2, p3,p4)
    }

    private fun createServiceAndProvideService() {
        val s1 = Service("اپیلاسیون تمام بدن", "180000")
        val s2 = Service("شمع صورت", "30000")
        val s3 = Service("اپیلاسیون دست و پا", "130000")
        val s4 = Service("کوتاهی مو", "86000")
        val s5 = Service("شنیون", "1800000")
        val s6 = Service("اصلاح ابرو", "32000")
        val s7 = Service("کاشت ناخن", "250000")
        val s8 = Service("رنگ مو", "350000")

        val p1 = ProvideService(
            1, 2, listOf(s1, s2, s4, s8, s6, s7, s5), Date(), "20000", "توضیحات"
        )
        val p2 =
            ProvideService(3, 3, listOf(s4, s1, s3, s4, s5, s7, s2), Date(), "50000", "")
        val p3 =
            ProvideService(2, 1, listOf(s7, s5, s3, s6, s8), Date(), "32000", "")
        val p4 =
            ProvideService(4, 4, listOf(s7, s5, s1, s3, s4, s6, s8), Date(), "63000", "")

        serviceViewModel.insertService(s1, s2, s3, s4, s5, s6, s7, s8)

        provideServiceViewModel.insertProvideService(p1)
        provideServiceViewModel.insertProvideService(p2)
        provideServiceViewModel.insertProvideService(p3)
        provideServiceViewModel.insertProvideService(p4)
    }
}