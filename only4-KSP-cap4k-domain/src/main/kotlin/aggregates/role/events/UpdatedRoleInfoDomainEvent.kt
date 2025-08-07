package aggregates.role.events

import aggregates.role.Role
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.annotation.DomainEvent

@DomainEvent(persist = false)
@Aggregate(
    aggregate = "Role",
    name = "UpdatedRoleInfoDomainEvent",
    type = Aggregate.TYPE_DOMAIN_EVENT,
    description = ""
)
class UpdatedRoleInfoDomainEvent(val entity: Role)
