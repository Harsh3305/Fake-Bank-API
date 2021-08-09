package com.example.fakebankapi.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {
    @Autowired
    lateinit var mockMvc : MockMvc
    val baseUrl = "/api/banks/"
    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks` () {
            //when // then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    jsonPath("$[0].accountNumber") {
                        value("314")
                    }
                    status { isOk() } }

        }

    }

    @Nested
    @DisplayName("getBank()")

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given bank account number` () {
            //given
            var accountNumber = "314"
            //when /then

            mockMvc.get("$baseUrl$accountNumber").
            andDo { print() }.
            andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.trust"){value(0.5)}
                jsonPath("$.transactionsFee"){value(1)}
            }
        }

        @Test
        fun `should return Not found if the account number does not exist` () {
            //given
            var accountNumber = "not found"

            //when / then
            mockMvc.get("$baseUrl$accountNumber").
            andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }

}