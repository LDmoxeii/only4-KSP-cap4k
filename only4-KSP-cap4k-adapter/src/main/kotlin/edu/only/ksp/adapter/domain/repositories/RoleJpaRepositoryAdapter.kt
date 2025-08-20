package edu.only.ksp.adapter.domain.repositories

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.domain.repo.AbstractJpaRepository
import com.only4.cap4k.ddd.domain.repo.querydsl.AbstractQuerydslRepository
import edu.only.ksp.domain.aggregates.role.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Component

interface RoleRepository : JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>, QuerydslPredicateExecutor<Role> {

    @Component
    @Aggregate(aggregate = "Role", name = "Role", type = Aggregate.TYPE_REPOSITORY, description = "")
    class RoleJpaRepositoryAdapter(
        jpaSpecificationExecutor: JpaSpecificationExecutor<Role>,
        jpaRepository: JpaRepository<Role, Long>,
    ) : AbstractJpaRepository<Role, Long>(
        jpaSpecificationExecutor,
        jpaRepository
    )

    @Component
    @Aggregate(aggregate = "Role", name = "Role", type = Aggregate.TYPE_REPOSITORY, description = "")
    class RoleQuerydslRepositoryAdapter(
        querydslPredicateExecutor: QuerydslPredicateExecutor<Role>,
    ) : AbstractQuerydslRepository<Role>(
        querydslPredicateExecutor
    )

}
