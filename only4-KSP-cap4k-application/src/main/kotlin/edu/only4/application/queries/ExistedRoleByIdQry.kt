package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam

object ExistedRoleByIdQry {
    class Request(
        val roleId: Long,
    ) : RequestParam<Response>

    class Response(
        val exists: Boolean,
    )
}
