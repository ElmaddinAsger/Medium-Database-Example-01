package com.elmaddinasger.mediumdatabaseexample.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface CustomerDao {

    @Query("SELECT * FROM CustomerTable")
    fun getAll(): List<CustomerEntity>

    @Query("SELECT * FROM CustomerTable WHERE customerId IN (:customerIds)")
    fun loadAllByIds(customerIds: LongArray): List<CustomerEntity>

    @Query("SELECT * FROM CustomerTable WHERE customerName LIKE :first LIMIT 1")
    fun findByName(first: String): CustomerEntity

    @Insert
    fun insert (customer: CustomerEntity) : Long

    @Update
    fun update(customer: CustomerEntity)

    @Delete
    fun delete(customer: CustomerEntity)
}