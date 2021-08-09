package com.example.fakebankapi.datasource

import com.example.fakebankapi.model.Bank

interface BankDataSource {
    fun getBanks() :Collection<Bank>
    abstract fun getBank(accountNumber: String): Bank
}