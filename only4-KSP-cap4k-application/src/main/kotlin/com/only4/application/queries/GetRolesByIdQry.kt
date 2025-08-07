package com.only4.application.queries

import aggregates.role.Role
import com.only4.cap4k.ddd.core.application.RequestParam

object GetRolesByIdQry {
    class Request(
        val id: Long = 0L,
    ) : RequestParam<Response>

    class Response(
        val role: Role = Role(),
    )
}
