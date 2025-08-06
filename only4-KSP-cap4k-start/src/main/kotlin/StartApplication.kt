package com.only4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

/**
 * @author cap4j-ddd-codegen
 */
@SpringBootApplication
@EntityScan(basePackages = ["com.only4.domain.aggregates"])
class StartApplication

fun main(args: Array<String>) {
    org.springframework.boot.SpringApplication.run(StartApplication::class.java, *args)
}
