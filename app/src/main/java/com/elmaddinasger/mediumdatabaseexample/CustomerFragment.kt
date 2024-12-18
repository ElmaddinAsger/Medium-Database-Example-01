package com.elmaddinasger.mediumdatabaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elmaddinasger.mediumdatabaseexample.databinding.FragmentCustomerBinding
import com.elmaddinasger.mediumdatabaseexample.db.AppDatabase
import com.elmaddinasger.mediumdatabaseexample.db.CustomerDao
import com.elmaddinasger.mediumdatabaseexample.db.CustomerEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBinding
    private lateinit var db: AppDatabase
    private lateinit var customerDao: CustomerDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(inflater,container,false)
        db = AppDatabase.getDatabase(requireContext())
        customerDao = db.customerDao()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            addCustomer()
        }

    }

    private fun addCustomer () {
        val customerAge = binding.edttxtCustomerAge.text.toString()
        val customerName = binding.edttxtCustomerName.text.toString()
        if (customerName.isNotEmpty() && customerAge.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val customerEntity = CustomerEntity(0,customerName,customerAge.toInt())
                customerDao.insert(customerEntity)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), "CustomerSaved!", Toast.LENGTH_SHORT).show()
                    binding.edttxtCustomerName.setText("")
                    binding.edttxtCustomerAge.setText("")
                }
            }
        }
    }
}