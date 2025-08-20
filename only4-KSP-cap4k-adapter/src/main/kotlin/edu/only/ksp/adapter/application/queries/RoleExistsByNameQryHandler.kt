package edu.only.ksp.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only.ksp.application.queries.RoleExistsByNameQry
import edu.only.ksp.application.queries._share.model.JRole
import edu.only.ksp.application.queries._share.model.name
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.exists
import org.springframework.stereotype.Service

@Service
class RoleExistsByNameQryHandler(
    private val sqlClient: KSqlClient,
) : Query<RoleExistsByNameQry.Request, RoleExistsByNameQry.Response> {

    override fun exec(request: RoleExistsByNameQry.Request): RoleExistsByNameQry.Response {
        return RoleExistsByNameQry.Response(sqlClient.exists(JRole::class) {
            where(table.name.eq(request.name))
        })
    }
}
