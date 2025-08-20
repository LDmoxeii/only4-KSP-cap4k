package edu.only.ksp.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only.ksp.application.validater.UniqueRoleName
import edu.only.ksp.domain.aggregates.role.AggRole
import edu.only.ksp.domain.aggregates.role.RolePermission
import edu.only.ksp.domain.aggregates.role.factory.RoleFactory
import org.springframework.stereotype.Service

object CreateRoleCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val role = Mediator.aggregates.create(
                AggRole::class.java,
                RoleFactory.Payload(
                    name = request.name,
                    description = request.description,
                    permissions = request.permissions,
                )
            )

            Mediator.uow.save()

            return Response(
                id = role.id!!,
                success = true,
            )
        }

    }

    class Request(
        @UniqueRoleName
        val name: String,
        val description: String,
        val permissions: List<RolePermission>,
    ) : RequestParam<Response>

    class Response(
        val id: Long = 0L,
        val success: Boolean,
    )
}
