package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.GetRolesByConditionQry
import edu.only4.domain.aggregates.role.QRole
import org.springframework.stereotype.Service

@Service
class GetRolesByConditionQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<GetRolesByConditionQry.Request, GetRolesByConditionQry.Response> {
    override fun exec(request: GetRolesByConditionQry.Request): GetRolesByConditionQry.Response =
        with(QRole.role) {
            val from = queryFactory.selectFrom(this)
            if (request.name.isNotBlank()) {
                from.where(name.containsIgnoreCase(request.name))
            }
            if (request.description.isNotBlank()) {
                from.where(description.containsIgnoreCase(request.description))
            }
            val roles = from.fetch()

            GetRolesByConditionQry.Response(
                roles = roles
            )
        }
}
