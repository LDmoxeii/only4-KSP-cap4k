package edu.only4.adapter.portal.api.response.common

class RoleResponse(
    val id: Long,
    val name: String,
    val description: String,
    val permissionCodes: List<RolePermissionResponse>,
)

class RolePermissionResponse(
    val code: String,
    val description: String,
    val assigned: Boolean,
)
