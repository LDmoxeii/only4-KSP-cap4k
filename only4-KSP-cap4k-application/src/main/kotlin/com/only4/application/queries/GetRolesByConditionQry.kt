package com.only4.application.queries

import aggregates.role.Role
import com.only4.cap4k.ddd.core.application.RequestParam

object GetRolesByConditionQry {
    class Request(
        val name: String = "",
        val description: String = "",
    ) : RequestParam<Response>

    class Response(
        val roles: List<Role> = emptyList(),
    )
}
