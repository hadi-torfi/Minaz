package com.haditorfi.minaz.data.customer

class CustomerRepositoryImpl(private val customerLocalDataSource: CustomerLocalDataSource) :
    CustomerRepository {
    override fun getAll(): List<Customer> = customerLocalDataSource.getAll()

    override fun insert(customers: Customer) = customerLocalDataSource.insert(customers)

    override fun delete(id: Long) = customerLocalDataSource.delete(id)

    override fun update(customer: Customer) = customerLocalDataSource.update(customer)
}