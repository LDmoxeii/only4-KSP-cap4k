package edu.only4.adapter.portal.api.request

class GetRolesByConditionRequest(
    val name: String = "",
    val description: String = "",
    val permissionCodes: List<String> = emptyList(),
)
