package edu.only4.adapter.application.queries

import com.only4.cap4k.ddd.core.application.query.Query
import com.querydsl.jpa.impl.JPAQueryFactory
import edu.only4.application.queries.GetAllRolesQry
import edu.only4.application.queries.draft.SIMPLE_ROLE
import edu.only4.application.queries.model.JRole
import edu.only4.application.queries.model.JRoleDraft
import edu.only4.application.queries.model.JRolePermissionDraft
import edu.only4.domain.aggregates.role.QRole
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service


@Service
class GetAllRolesQryHandler(
    private val queryFactory: JPAQueryFactory,
    private val sqlClient: KSqlClient,
) : Query<GetAllRolesQry.Request, GetAllRolesQry.Response> {
    override fun exec(request: GetAllRolesQry.Request): GetAllRolesQry.Response =
        jimmer()

    private fun queryDSL(): GetAllRolesQry.Response = with(QRole.role) {
        val roles = queryFactory.selectFrom(this).fetch()
        val results = roles.map {
            JRoleDraft.Builder()
                .id(it.id)
                .name(it.name)
                .description(it.description ?: "")
                .rolePermissions(it.rolePermissions.map { permission ->
                    JRolePermissionDraft.Builder()
                        .id(permission.id)
                        .permissionCode(permission.permissionCode)
                        .permissionRemark(permission.permissionRemark)
                        .build()
                })
                .build()
        }

        GetAllRolesQry.Response(results)
    }

    private fun jimmer(): GetAllRolesQry.Response {
        val roles = sqlClient.executeQuery(JRole::class) {
            select(table.fetch(SIMPLE_ROLE))
        }

        return GetAllRolesQry.Response(roles)
    }

}

