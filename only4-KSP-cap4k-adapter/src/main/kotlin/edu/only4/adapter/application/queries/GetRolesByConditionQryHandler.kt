package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.application.queries.GetRolesByConditionQry
import edu.only4.application.queries._share.model.ROLE
import edu.only4.application.queries._share.model.description
import edu.only4.application.queries._share.model.name
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.babyfish.jimmer.sql.kt.ast.expression.`like?`
import org.springframework.stereotype.Service

@Service
class GetRolesByConditionQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetRolesByConditionQry.Request, GetRolesByConditionQry.Response> {

    override fun exec(request: GetRolesByConditionQry.Request): GetRolesByConditionQry.Response {
        return GetRolesByConditionQry.Response(
            sqlClient.findAll(ROLE) {
                where(table.name `like?` request.name)
                where(table.description `ilike?` request.description)
            }
        )
    }
}
