package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.GetRoleByNameQry
import edu.only4.application.queries.model.ROLE
import edu.only4.application.queries.model.name
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

@Service
class GetRoleByNameQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetRoleByNameQry.Request, GetRoleByNameQry.Response> {
    override fun exec(request: GetRoleByNameQry.Request): GetRoleByNameQry.Response {
        return GetRoleByNameQry.Response(
            sqlClient.findOneOrNull(ROLE) {
                where(table.name.eq(request.roleName))
            }
        )
    }
}
