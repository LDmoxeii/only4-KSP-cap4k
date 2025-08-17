package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.GetRolePermissionsByIdQry
import edu.only4.application.queries._share.draft.JRole.RolePermissionInfo
import edu.only4.application.queries._share.model.id
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

@Service
class GetRolePermissionsByIdQryHandler(
    private val sqlClient: KSqlClient,
    ) : Query<GetRolePermissionsByIdQry.Request, GetRolePermissionsByIdQry.Response> {
    override fun exec(request: GetRolePermissionsByIdQry.Request): GetRolePermissionsByIdQry.Response {

        return GetRolePermissionsByIdQry.Response(
            sqlClient.findOneOrNull(RolePermissionInfo::class) {
                where(table.id.eq(request.id))
            }
        )
    }
}
