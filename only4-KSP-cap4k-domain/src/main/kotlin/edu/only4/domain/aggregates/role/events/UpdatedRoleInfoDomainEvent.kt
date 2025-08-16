package edu.only4.domain.aggregates.role.events

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent
import edu.only4.domain.aggregates.role.Role

@DomainEvent(persist = true)
@Aggregate(
    aggregate = "Role",
    name = "UpdatedRoleInfoDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class UpdatedRoleInfoDomainEvent(val entity: Role)
