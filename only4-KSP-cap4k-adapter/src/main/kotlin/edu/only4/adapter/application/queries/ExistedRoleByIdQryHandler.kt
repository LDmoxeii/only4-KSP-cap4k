package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.ExistedRoleByIdQry
import edu.only4.application.queries.model.JRole
import edu.only4.application.queries.model.id
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.exists
import org.springframework.stereotype.Service

@Service
class ExistedRoleByIdQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ExistedRoleByIdQry.Request, ExistedRoleByIdQry.Response> {

    override fun exec(request: ExistedRoleByIdQry.Request): ExistedRoleByIdQry.Response {
        return ExistedRoleByIdQry.Response(
            sqlClient.exists(JRole::class) {
                where(table.id.eq(request.roleId))
            }
        )
    }
}
