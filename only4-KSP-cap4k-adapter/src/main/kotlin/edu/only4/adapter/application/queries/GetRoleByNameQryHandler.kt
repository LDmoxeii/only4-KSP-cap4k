package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.GetRoleByNameQry
import edu.only4.domain.aggregates.role.QRole
import edu.only4.domain.aggregates.role.QRole.role
import org.springframework.stereotype.Service

@Service
class GetRoleByNameQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<GetRoleByNameQry.Request, GetRoleByNameQry.Response> {
    override fun exec(request: GetRoleByNameQry.Request): GetRoleByNameQry.Response =
        with(role) {
            val role = (queryFactory.selectFrom(this)
                .where(name.eq(request.roleName))
                .fetchFirst()
                ?: throw IllegalArgumentException("Role with name '${request.roleName}' not found"))

            GetRoleByNameQry.Response(role)
        }
}
