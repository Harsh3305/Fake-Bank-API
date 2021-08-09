package com.example.fakebankapi.datasource.mock

import com.example.fakebankapi.datasource.BankDataSource
import com.example.fakebankapi.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {
    private val listOfBanks = mutableListOf(
        Bank("314", 0.5, 1),
        Bank("012", 0.6, 2),
        Bank("123", 0.7, 3)
    )
    override fun getBanks(): Collection<Bank> {
        return listOfBanks
    }

    override fun getBank(accountNumber: String): Bank {
        return getBanks().first { it.accountNumber.equals(accountNumber) }
    }

    override fun createdBank(bank:Bank) : Bank{
        if (listOfBanks.any { it.accountNumber.equals(bank.accountNumber) }) {
            throw IllegalArgumentException("Bank with account ${bank.accountNumber} already exist")
        }
        listOfBanks.add(bank)
        return bank
    }
}