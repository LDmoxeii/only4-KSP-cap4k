package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.RoleExistsByNameQry
import edu.only4.domain.aggregates.role.QRole
import org.springframework.stereotype.Service

@Service
class ExistedRoleByNameQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<RoleExistsByNameQry.Request, RoleExistsByNameQry.Response> {

    override fun exec(request: RoleExistsByNameQry.Request): RoleExistsByNameQry.Response {

        val exists = queryFactory.selectFrom(QRole.role)
            .where(QRole.role.name.eq(request.name))
            .fetchFirst() != null

        return RoleExistsByNameQry.Response(
            exists = exists
        )
    }
}
