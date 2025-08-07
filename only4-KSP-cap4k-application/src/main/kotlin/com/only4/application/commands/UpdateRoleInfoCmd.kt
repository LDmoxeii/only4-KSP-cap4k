package com.only4.application.commands

import aggregates.role.Role
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import org.springframework.stereotype.Service

object UpdateRoleInfoCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            Mediator.repo.findOne(
                JpaPredicate.byId(Role::class.java, request.roleId)
            ).orElseThrow { IllegalArgumentException("Role with ID ${request.roleId} not found") }
                .apply { updateRoleInfo(request.name, request.description) }

            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long = 0L,
        val name: String = "",
        val description: String = "",
    ) : RequestParam<Response>

    class Response(
        val success: Boolean = false,
    )
}
