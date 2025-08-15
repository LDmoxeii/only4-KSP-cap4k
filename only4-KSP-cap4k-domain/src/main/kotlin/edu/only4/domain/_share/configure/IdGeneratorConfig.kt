package edu.only4.domain._share.configure

import com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator
import org.hibernate.annotations.GenericGenerator

@GenericGenerator(
    name = "snowflake",
    type = SnowflakeIdentifierGenerator::class
)
class IdGeneratorConfig
