package edu.only4.application.domain.subscribers

import edu.only4.domain.aggregates.role.events.UpdatedRoleInfoDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class UpdatedRoleInfoDomainEventSubscriber {

    @EventListener(UpdatedRoleInfoDomainEvent::class)
    fun on(event: UpdatedRoleInfoDomainEvent) {
        println("UpdatedRoleInfoDomainEventSubscriber.on: ${event.entity.name}")
    }
}
