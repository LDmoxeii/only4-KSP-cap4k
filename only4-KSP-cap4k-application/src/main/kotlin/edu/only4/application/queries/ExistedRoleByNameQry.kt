package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam

object ExistedRoleByNameQry {
    class Request(
        val name: String,
    ) : RequestParam<Response>

    class Response(
        val exists: Boolean,
    )
}
