package edu.only.ksp.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only.ksp.domain.aggregates.role.AggRole
import org.springframework.stereotype.Service

object UpdateRoleInfoCmd {

    @Service
    class Handler : Command<Request, Response> {

        override fun exec(request: Request): Response {

            Mediator.aggregates.getById(AggRole.Id(request.roleId))?.apply {
                updateRoleInfo(request.description)
            } ?: throw IllegalArgumentException("Role with ID ${request.roleId} not found")

            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long,
        val description: String,
    ) : RequestParam<Response>

    class Response(
        val success: Boolean,
    )
}
