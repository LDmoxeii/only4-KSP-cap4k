package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.application.queries._share.model.JRole

object GetRolesByConditionQry {
    class Request(
        val name: String,
        val description: String,
    ) : RequestParam<Response>

    class Response(
        val roles: List<JRole>,
    )
}
