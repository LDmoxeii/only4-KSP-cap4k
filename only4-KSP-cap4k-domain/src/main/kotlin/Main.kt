package com.only4

/**
 * @author cap4j-ddd-codegen
 */
@SpringBootApplication
//@EnableFeignClients
//@EnableScheduling
@EnableJpaRepositories(basePackages = "com.only4.adapter.domain.repositories")
@EntityScan(basePackages = ["com.only4.domain.aggregates"])
object StartApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(StartApplication::class.java, *args)
    }
}
