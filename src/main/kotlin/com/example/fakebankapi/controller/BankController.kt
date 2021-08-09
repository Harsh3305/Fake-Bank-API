package com.example.fakebankapi.controller

import com.example.fakebankapi.model.Bank
import com.example.fakebankapi.service.BankService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController (private val service: BankService ){
    @GetMapping
    fun getBanks () : Collection<Bank> {
        return service.getBanks()
    }
    @GetMapping("/{accountNumber}")
    fun getBank (@PathVariable accountNumber: String ) : Bank{
        return service.getBank(accountNumber)
    }
    @ExceptionHandler(NoSuchElementException :: class)
    fun handleNotFound(e: NoSuchElementException) : ResponseEntity<String>{
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

}