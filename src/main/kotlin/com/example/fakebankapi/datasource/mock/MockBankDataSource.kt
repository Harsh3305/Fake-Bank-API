package com.example.fakebankapi.datasource.mock

import com.example.fakebankapi.datasource.BankDataSource
import com.example.fakebankapi.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {
    override fun getBanks(): Collection<Bank> {
//        TODO("Not yet implemented")
        var listOfBanks = listOf<Bank> (
            Bank("314", 0.5, 1),
            Bank("012", 0.6, 2),
            Bank("123", 0.7, 3)
        )
        return listOfBanks
    }

    override fun getBank(accountNumber: String): Bank {
        return getBanks().first({it.accountNumber.equals(accountNumber)})
    }
}