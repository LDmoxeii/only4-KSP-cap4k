package edu.only4.application.subscribers.integration

import edu.only4.application.distributed.events.UpdatedRoleInfoIntegrationEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class UpdatedRoleInfoIntegrationEventSubscriber {

    @TransactionalEventListener(
        fallbackExecution = true,
        classes = [UpdatedRoleInfoIntegrationEvent::class]
    )
    fun on(event: UpdatedRoleInfoIntegrationEvent) {
        println("UpdatedRoleInfoIntegrationEvent.on: ${event.name}")
    }
}
