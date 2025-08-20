package edu.only.ksp.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only.ksp.application.queries._share.model.JRole

object GetAllRolesQry {

    class Request : RequestParam<Response>
    class Response(
        val roles: List<JRole> = emptyList(),
    )
}
