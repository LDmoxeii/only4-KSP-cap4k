package com.only4.application.commands

import aggregates.role.RolePermission
import aggregates.role.factory.RoleFactory
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
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
        val name: String = "",
        val description: String = "",
        val permissions: List<RolePermission> = emptyList(),
    ) : RequestParam<Response>

    class Response(
        val id: Long = 0L,
        val success: Boolean = false,
    )
}
