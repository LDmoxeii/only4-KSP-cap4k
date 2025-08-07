package com.only4.application.commands

import aggregates.role.Role
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import org.springframework.stereotype.Service

object DeleteRoleCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val role = Mediator.repo.findOne(
                JpaPredicate.byId(Role::class.java, request.roleId),
                false
            ).orElseThrow { IllegalArgumentException("Role with ID ${request.roleId} not found") }

            Mediator.uow.remove(role)
            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long = 0L,
    ) : RequestParam<Response>

    class Response(
        val success: Boolean = false,
    )
}
