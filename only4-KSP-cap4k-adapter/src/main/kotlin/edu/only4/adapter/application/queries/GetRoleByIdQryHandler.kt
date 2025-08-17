package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.GetRoleByIdQry
import edu.only4.application.queries._share.model.ROLE
import edu.only4.application.queries._share.model.id
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

@Service
class GetRoleByIdQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetRoleByIdQry.Request, GetRoleByIdQry.Response> {
    override fun exec(request: GetRoleByIdQry.Request): GetRoleByIdQry.Response {
        return GetRoleByIdQry.Response(
            sqlClient.findOneOrNull(ROLE) {
                where(table.id.eq(request.id))
            }
        )
    }
}
