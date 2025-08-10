package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.RoleExistsByIdQry
import edu.only4.domain.aggregates.role.QRole
import org.springframework.stereotype.Service

@Service
class RoleExistsByIdQryHandler(
    private val queryFactory: JPAQueryFactory,
) : Query<RoleExistsByIdQry.Request, RoleExistsByIdQry.Response> {
    override fun exec(request: RoleExistsByIdQry.Request): RoleExistsByIdQry.Response {
        val exists = with(QRole.role) {
            queryFactory.selectFrom(this)
                .where(id.eq(request.roleId))
                .fetchFirst() != null
        }
        return RoleExistsByIdQry.Response(
            exists = exists
        )
    }
}
