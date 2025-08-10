package edu.only4.application.commands

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command
import com.only4.cap4k.ddd.domain.aggregate.JpaAggregatePredicate
import edu.only4.domain.aggregates.role.AggRole
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

object UpdateRoleInfoCmd {

    @Service
    class Handler : Command<Request, Response> {

        @Transactional
        override fun exec(request: Request): Response {
            Mediator.aggregates.findOne(
                JpaAggregatePredicate.byId(AggRole::class.java, AggRole.Id(request.roleId).value)
            ).orElseThrow { IllegalArgumentException("Role with ID ${request.roleId} not found") }
                .apply { updateRoleInfo(request.name, request.description) }

            Mediator.uow.save()

            return Response(success = true)
        }

    }

    class Request(
        val roleId: Long,
        val name: String,
        val description: String,
    ) : RequestParam<Response>

    class Response(
        val success: Boolean,
    )
}
