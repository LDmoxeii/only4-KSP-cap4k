package edu.only4.adapter.portal.api.request

class CreateRoleRequest(
    val name: String = "",
    val description: String = "",
    val permissionCodes: List<String> = emptyList(),
)
