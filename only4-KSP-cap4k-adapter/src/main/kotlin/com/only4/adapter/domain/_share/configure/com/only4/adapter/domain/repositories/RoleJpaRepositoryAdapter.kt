package com.only4.adapter.domain._share.configure.com.only4.adapter.domain.repositories

import aggregates.role.Role
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.domain.repo.AbstractJpaRepository
import com.only4.cap4k.ddd.domain.repo.AggregateRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Component

interface RoleRepository : AggregateRepository<Role, Long> {

    @Component
    @Aggregate(aggregate = "Role", name = "Role", type = Aggregate.TYPE_REPOSITORY, description = "")
    class RoleJpaRepositoryAdapter(
        jpaSpecificationExecutor: JpaSpecificationExecutor<Role>,
        jpaRepository: JpaRepository<Role, Long>,
    ) : AbstractJpaRepository<Role, Long>(
        jpaSpecificationExecutor,
        jpaRepository
    )

}
