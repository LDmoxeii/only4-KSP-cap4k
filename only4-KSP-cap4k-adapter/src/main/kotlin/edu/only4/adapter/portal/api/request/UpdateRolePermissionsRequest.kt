package edu.only4.adapter.portal.api.request

class UpdateRolePermissionsRequest(
    val roleId: Long = 0L,
    val permissionCodes: List<String> = emptyList(),
)
