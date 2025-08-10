package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.GetRoleByIdQry
import edu.only4.domain.aggregates.role.QRole
import org.springframework.stereotype.Service

@Service
class GetRoleByIdQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<GetRoleByIdQry.Request, GetRoleByIdQry.Response> {
    override fun exec(request: GetRoleByIdQry.Request): GetRoleByIdQry.Response {
        val role = with(QRole.role) {
            queryFactory.selectFrom(this)
                .where(id.eq(request.id))
                .fetchOne()
                ?: throw IllegalArgumentException("Role with id '${request.id}' not found")
        }

        return GetRoleByIdQry.Response(role)
    }
}
