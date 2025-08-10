package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.domain.aggregates.role.Role

object GetAllRolesQry {

    class Request : RequestParam<Response>
    class Response(
        val roles: List<Role> = emptyList(),
    )
}
