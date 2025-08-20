package edu.only.ksp

import org.babyfish.jimmer.spring.repository.EnableJimmerRepositories
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author cap4j-ddd-codegen
 */
@SpringBootApplication
@EnableScheduling
@AutoConfigurationPackage(basePackages = ["com.only.engine"])
@EnableJimmerRepositories(basePackages = ["edu.only.adapter.application.repositories"])
@EnableJpaRepositories(basePackages = ["edu.only.ksp.adapter.domain.repositories"])
@EntityScan(basePackages = ["edu.only.ksp.domain.aggregates"])
class StartApplication

fun main(args: Array<String>) {
    SpringApplication.run(StartApplication::class.java, *args)
}
