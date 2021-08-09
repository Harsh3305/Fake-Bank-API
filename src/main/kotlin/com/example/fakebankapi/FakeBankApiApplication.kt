package com.example.fakebankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FakeBankApiApplication

fun main(args: Array<String>) {
    runApplication<FakeBankApiApplication>(*args)
}
