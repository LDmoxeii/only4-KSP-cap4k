package edu.only.ksp.domain.aggregates.permission

class Permission(
    val code: String = "",
    val groupName: String = "",
    val remark: String = "",
) {
    companion object {
        val ALL = listOf(
            Permission(PermissionDefinitions.PERMISSION_READ, PermissionGroup.SYSTEM_ACCESS, "查看权限"),
            Permission(PermissionDefinitions.PERMISSION_UPDATE, PermissionGroup.SYSTEM_ACCESS, "修改权限"),
            Permission(PermissionDefinitions.PERMISSION_DELETE, PermissionGroup.SYSTEM_ACCESS, "删除权限"),
            Permission(PermissionDefinitions.PERMISSION_CREATE, PermissionGroup.SYSTEM_ACCESS, "创建权限"),
            Permission(PermissionDefinitions.ROLE_READ, PermissionGroup.SYSTEM_ACCESS, "查看角色"),
            Permission(PermissionDefinitions.ROLE_UPDATE, PermissionGroup.SYSTEM_ACCESS, "修改角色"),
            Permission(PermissionDefinitions.ROLE_DELETE, PermissionGroup.SYSTEM_ACCESS, "删除角色"),
            Permission(PermissionDefinitions.ROLE_CREATE, PermissionGroup.SYSTEM_ACCESS, "创建角色"),
        )

        fun getAllPermission(): List<Permission> {
            return ALL
        }
    }
}
