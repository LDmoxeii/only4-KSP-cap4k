package edu.only4.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import edu.only4.domain.aggregates.role.AggRole
import org.springframework.stereotype.Service

object DeleteRoleCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            Mediator.aggregates.removeByIds(
                request.roleIds.map(AggRole::Id)
            )

            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleIds: List<Long>,
    ) : RequestParam<Response>

    class Response(
        val success: Boolean,
    )
}
