package edu.only.ksp.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only.ksp.application.queries.GetRoleByNameQry
import edu.only.ksp.application.queries._share.fetcher.RoleFetcher.ROLE
import edu.only.ksp.application.queries._share.model.name
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
