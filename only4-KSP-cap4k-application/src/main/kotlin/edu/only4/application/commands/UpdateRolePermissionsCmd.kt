package edu.only4.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.domain.aggregate.JpaAggregatePredicate
import edu.only4.domain.aggregates.role.AggRole
import edu.only4.domain.aggregates.role.RolePermission
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

object UpdateRolePermissionsCmd {
    @Service
    class Handler : Command<Request, Response> {

        @Transactional
        override fun exec(request: Request): Response {
            Mediator.aggregates.findOne(
                JpaAggregatePredicate.byId(AggRole::class.java, AggRole.Id(request.roleId).value)
            ).orElseThrow { IllegalArgumentException("Role with ID ${request.roleId} not found") }
                .apply { updateRolePermission(request.permissions) }

            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long = 0L,
        val permissions: List<RolePermission> = emptyList(),
    ) : RequestParam<Response>

    class Response(
        val success: Boolean,
    )
}
