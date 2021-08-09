package com.example.fakebankapi.datasource.mock

import com.example.fakebankapi.datasource.mock.MockBankDataSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    private val mockDataSource : MockBankDataSource = MockBankDataSource()
    @Test
    fun `should provide collections of banks`() {
        // Given

        // When

        val bank = mockDataSource.getBanks()

        // then

        assertThat(bank).isNotEmpty
        assertThat(bank.size).isGreaterThanOrEqualTo(3)
    }
    @Test
    fun `should provide mock data` () {
        // given
        // when

        var bank = mockDataSource.getBanks()

        // then

        assertThat(bank).allMatch{it.accountNumber.isNotBlank()}
        assertThat(bank).allMatch{it.transactionsFee != 0}
        assertThat(bank).allMatch{it.trust != 0.0}
    }
}