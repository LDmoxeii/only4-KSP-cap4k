package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.GetAllRolesQry
import edu.only4.application.queries.model.JRole
import edu.only4.application.queries.model.ROLE
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service


@Service
class GetAllRolesQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetAllRolesQry.Request, GetAllRolesQry.Response> {
    override fun exec(request: GetAllRolesQry.Request): GetAllRolesQry.Response {
        val roles = sqlClient.executeQuery(JRole::class) {
            select(table.fetch(ROLE))
        }

        return GetAllRolesQry.Response(roles)
    }
}

