package edu.only4.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.domain.aggregate.JpaAggregatePredicate
import edu.only4.domain.aggregates.role.AggRole
import org.springframework.stereotype.Service

object DeleteRoleCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            val role = Mediator.aggregates.findOne(
                JpaAggregatePredicate.byId(AggRole::class.java, AggRole.Id(request.roleId))
            ).orElseThrow { IllegalArgumentException("Role with ID ${request.roleId} not found") }

            Mediator.uow.remove(role)
            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long,
    ) : RequestParam<Response>

    class Response(
        val success: Boolean,
    )
}
