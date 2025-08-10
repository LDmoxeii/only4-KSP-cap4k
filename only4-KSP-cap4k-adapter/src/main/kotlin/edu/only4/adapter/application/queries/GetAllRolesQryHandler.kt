package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.GetAllRolesQry
import edu.only4.domain.aggregates.role.QRole
import org.springframework.stereotype.Service


@Service
class GetAllRolesQryHandler(
    private val queryFactory: JPAQueryFactory,

    ) : Query<GetAllRolesQry.Request, GetAllRolesQry.Response> {
    override fun exec(request: GetAllRolesQry.Request): GetAllRolesQry.Response {
        val roles = with(QRole.role) {
            queryFactory.selectFrom(this)
                .fetch()
        }

        return GetAllRolesQry.Response(
            roles
        )
    }


}
