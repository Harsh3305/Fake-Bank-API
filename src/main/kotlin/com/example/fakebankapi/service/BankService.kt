package com.example.fakebankapi.service

import com.example.fakebankapi.datasource.BankDataSource
import com.example.fakebankapi.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (private val dataSource: BankDataSource) {
    fun getBanks () : Collection<Bank>{
        return dataSource.getBanks();
    }

    fun getBank(accountNumber: String): Bank {
        return dataSource.getBank(accountNumber);
    }

    fun addBank(bank:Bank) : Bank{
        return dataSource.createdBank(bank)
    }
}