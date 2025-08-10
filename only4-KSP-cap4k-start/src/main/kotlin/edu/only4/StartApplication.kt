package edu.only4

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author cap4j-ddd-codegen
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = ["edu.only4.adapter.domain.repositories"])
@EntityScan(basePackages = ["edu.only4.domain.aggregates"])
class StartApplication

fun main(args: Array<String>) {
    SpringApplication.run(StartApplication::class.java, *args)
}
