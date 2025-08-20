package edu.only.ksp.adapter.portal.api.request

class UpdateRolePermissionsRequest(
    val roleId: Long = 0L,
    val permissionCodes: List<String> = emptyList(),
)
