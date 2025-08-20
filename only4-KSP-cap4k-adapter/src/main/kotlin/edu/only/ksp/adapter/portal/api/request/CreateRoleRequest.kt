package edu.only.ksp.adapter.portal.api.request

import jakarta.validation.constraints.NotBlank

data class CreateRoleRequest(
    @field:NotBlank(message = "用户名不能为空")
    val name: String,

    val description: String,

    val permissionCodes: List<String>,
)
