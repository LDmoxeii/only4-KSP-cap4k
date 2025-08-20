package edu.only.ksp.domain.aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.Aggregate
import edu.only.ksp.domain.aggregates.role.events.UpdatedRoleInfoDomainEvent
import edu.only.ksp.domain.aggregates.role.events.UpdatedRolePermissionsDomainEvent
import edu.only.ksp.domain.aggregates.role.factory.RoleFactory

/**
 * Role聚合封装
 *
 *
 * @author cap4j-ddd-codegen
 * @date 2025/08/10
 */
class AggRole(
    payload: RoleFactory.Payload? = null,
) : Aggregate.Default<Role>(payload) {

    val id by lazy { root.id }

    class Id(key: Long) : com.only4.cap4k.ddd.core.domain.aggregate.Id.Default<AggRole, Long>(key)

    fun updateRoleInfo(description: String?) = with(root) {
        root.description = description
        registerDomainEvent { UpdatedRoleInfoDomainEvent(root) }
    }

    fun updateRolePermission(newPermissions: List<RolePermission>) = with(root) {
        val currentPermissionMap = rolePermissions.associateBy { it.permissionCode }
        val newPermissionMap = newPermissions.associateBy { it.permissionCode }

        // 移除不存在于新权限列表中的旧权限
        val toRemove = currentPermissionMap.keys - newPermissionMap.keys
        rolePermissions.removeAll { it.permissionCode in toRemove }

        // 添加新权限
        val toAdd = newPermissionMap.keys - currentPermissionMap.keys
        rolePermissions.addAll(toAdd.mapNotNull { newPermissionMap[it] })

        registerDomainEvent { UpdatedRolePermissionsDomainEvent(root) }
    }
}
