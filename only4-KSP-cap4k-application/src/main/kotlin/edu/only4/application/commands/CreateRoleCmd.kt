package edu.only4.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.domain.aggregates.role.RolePermission
import edu.only4.domain.aggregates.role.factory.RoleFactory
import org.springframework.stereotype.Service

object CreateRoleCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val role = Mediator.fac.create(
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
        val name: String,
        val description: String,
        val permissions: MutableList<RolePermission>,
    ) : RequestParam<Response>

    class Response(
        val id: Long = 0L,
        val success: Boolean,
    )
}
