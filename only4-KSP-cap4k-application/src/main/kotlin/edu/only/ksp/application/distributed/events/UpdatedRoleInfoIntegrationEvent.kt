package edu.only.ksp.application.distributed.events

import com.only4.cap4k.ddd.core.application.event.annotation.AutoRelease
import com.only4.cap4k.ddd.core.application.event.annotation.IntegrationEvent
import edu.only.ksp.domain.aggregates.role.events.UpdatedRoleInfoDomainEvent
import org.springframework.core.convert.converter.Converter

@IntegrationEvent(value = "UpdatedRoleInfoIntegrationEvent", subscriber = "\${spring.application.name}")
@AutoRelease(sourceDomainEventClass = UpdatedRoleInfoDomainEvent::class)
class UpdatedRoleInfoIntegrationEvent(
    val name: String = "",
) : Converter<UpdatedRoleInfoDomainEvent, UpdatedRoleInfoIntegrationEvent> {

    override fun convert(source: UpdatedRoleInfoDomainEvent): UpdatedRoleInfoIntegrationEvent {
        return UpdatedRoleInfoIntegrationEvent(source.entity.name)
    }
}
