package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.RoleExistsByNameQry
import edu.only4.domain.aggregates.role.QRole.role
import org.springframework.stereotype.Service

@Service
class ExistedRoleByNameQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<RoleExistsByNameQry.Request, RoleExistsByNameQry.Response> {

    override fun exec(request: RoleExistsByNameQry.Request): RoleExistsByNameQry.Response =
        with(role) {
            val exists = queryFactory.selectFrom(role)
                .where(name.eq(request.name))
                .fetchFirst() != null
            RoleExistsByNameQry.Response(exists = exists)
        }
}
