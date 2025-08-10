package edu.only4.application.domain.subscribers

import edu.only4.domain.aggregates.role.events.UpdatedRolePermissionsDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class UpdatedRolePermissionsDomainEventSubscriber {

    @EventListener(UpdatedRolePermissionsDomainEvent::class)
    fun on(event: UpdatedRolePermissionsDomainEvent) {
        println("UpdatedRolePermissionsDomainEventSubscriber.on() called with event: $event")
    }
}
