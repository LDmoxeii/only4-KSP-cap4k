package edu.only4.domain.aggregates.role.factory

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import edu.only4.domain.aggregates.role.Role
import edu.only4.domain.aggregates.role.RolePermission
import org.springframework.stereotype.Service

@Service
@Aggregate(aggregate = "Role", name = "RoleFactory", type = Aggregate.TYPE_FACTORY, description = "")
class RoleFactory : AggregateFactory<RoleFactory.Payload, Role> {
    override fun create(payload: Payload): Role {
        return Role(
            name = payload.name,
            description = payload.description,
            rolePermissions = payload.permissions
        )
    }

    @Aggregate(aggregate = "Role", name = "RolePayload", type = Aggregate.TYPE_FACTORY_PAYLOAD, description = "")
    class Payload(
        val name: String,
        val description: String,
        val permissions: MutableList<RolePermission>,
    ) : AggregatePayload<Role>
}
