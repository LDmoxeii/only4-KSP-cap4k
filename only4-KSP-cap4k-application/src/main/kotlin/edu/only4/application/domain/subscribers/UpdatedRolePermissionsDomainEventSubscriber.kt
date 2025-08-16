package edu.only4.application.domain.subscribers

import edu.only4.domain.aggregates.role.events.UpdatedRolePermissionsDomainEvent
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
