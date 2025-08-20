package edu.only.ksp.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only.ksp.application.queries._share.model.JRole

object GetRoleByIdQry {
    class Request(
        val id: Long,
    ) : RequestParam<Response>

    class Response(
        val role: JRole? = null,
    )
}
