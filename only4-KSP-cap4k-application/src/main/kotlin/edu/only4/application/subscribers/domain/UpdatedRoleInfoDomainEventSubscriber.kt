package edu.only4.application.subscribers.domain

import edu.only4.domain.aggregates.role.events.UpdatedRoleInfoDomainEvent
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
