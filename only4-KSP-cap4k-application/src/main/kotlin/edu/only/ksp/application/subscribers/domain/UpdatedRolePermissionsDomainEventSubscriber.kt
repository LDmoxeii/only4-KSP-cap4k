package edu.only.ksp.application.subscribers.domain

import edu.only.ksp.domain.aggregates.role.events.UpdatedRolePermissionsDomainEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UpdatedRolePermissionsDomainEventSubscriber {

    @TransactionalEventListener(
        fallbackExecution = true,
        classes = [UpdatedRolePermissionsDomainEvent::class],
    )
    fun on(event: UpdatedRolePermissionsDomainEvent) {
        println("UpdatedRolePermissionsDomainEventSubscriber.on() called with event: $event")
    }
}
