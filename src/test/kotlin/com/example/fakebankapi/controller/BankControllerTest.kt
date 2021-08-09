package com.example.fakebankapi.controller

import com.example.fakebankapi.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    private val mockMvc : MockMvc,
    private val objectMapper : ObjectMapper

) {



    val baseUrl = "/api/banks/"
    @Nested
    @DisplayName("GET /api/banks")
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
    @DisplayName("GET /api/banks/{accountNumber}")

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

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class  PostNewBank{
        @Test
        fun `should add new bank` () {
            //given
            val newBank = Bank("12345", 0.6, 2)

            //when
            val performPost : ResultActionsDsl = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost.
            andDo { print() }.
            andExpect {
                jsonPath("$.accountNumber") {
                    value("12345")
                }
                jsonPath("$.trust") {
                    value(0.6)
                }
                jsonPath("$.transactionsFee") {
                    value(2)
                }
                status { isCreated() }
            }
        }

        @Test
        fun `should return BAD request if the given bank account number already exists` () {
            //given
            val invalidBank =Bank("314", 0.5, 1)

            //when
            val performPost : ResultActionsDsl = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            //then
            performPost.
            andDo { print() }.
            andExpect {
                status { isBadRequest() }
            }
        }
    }
}