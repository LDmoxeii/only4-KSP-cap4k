package com.only4.application.queries

import aggregates.role.Role
import com.only4.cap4k.ddd.core.application.RequestParam

object GetRoleByNameQry {
    class Request(
        val roleName: String = "",
    ) : RequestParam<Response>

    class Response(
        val role: Role = Role(),
    )
}
