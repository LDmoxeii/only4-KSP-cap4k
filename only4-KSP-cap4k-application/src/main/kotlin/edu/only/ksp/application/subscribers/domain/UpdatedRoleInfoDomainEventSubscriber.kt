package edu.only.ksp.application.subscribers.domain

import edu.only.ksp.domain.aggregates.role.events.UpdatedRoleInfoDomainEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UpdatedRoleInfoDomainEventSubscriber {

    @TransactionalEventListener(
        fallbackExecution = true,
        classes = [UpdatedRoleInfoDomainEvent::class]
    )
    fun on(event: UpdatedRoleInfoDomainEvent) {
        println("UpdatedRoleInfoDomainEventSubscriber.on: ${event.entity.name}")
    }
}
