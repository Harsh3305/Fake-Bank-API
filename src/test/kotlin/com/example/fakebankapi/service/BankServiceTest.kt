package com.example.demo.service

import com.example.fakebankapi.datasource.BankDataSource
import com.example.fakebankapi.service.BankService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    private val dataSource : BankDataSource = mockk(relaxed = true)
    private val  bankService : BankService = BankService(dataSource)
    @Test
    fun `should call its data source it retrieve banks` () {
        //given

        //when
        val bank = bankService.getBanks()
        //then
        verify (exactly = 1) { dataSource.getBanks() }
    }
}