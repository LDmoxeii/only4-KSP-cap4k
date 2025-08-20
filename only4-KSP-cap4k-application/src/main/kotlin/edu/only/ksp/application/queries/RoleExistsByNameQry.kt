package edu.only.ksp.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam

object RoleExistsByNameQry {
    data class Request(
        val name: String,
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean,
    )
}
