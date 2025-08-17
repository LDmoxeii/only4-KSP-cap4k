package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.application.queries._share.model.JRole

object GetRoleByNameQry {
    class Request(
        val roleName: String,
    ) : RequestParam<Response>

    class Response(
        val role: JRole? = null,
    )
}
