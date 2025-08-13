package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.application.queries.model.JRole

object GetAllRolesQry {

    class Request : RequestParam<Response>
    class Response(
        val roles: List<JRole> = emptyList(),
    )
}
