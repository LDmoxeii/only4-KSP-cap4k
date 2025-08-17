package edu.only4.application.queries

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.application.queries._share.draft.JRole.RolePermissionInfo

object GetRolePermissionsByIdQry {
    class Request(
        val id: Long,
    ) : RequestParam<Response>

    class Response(
        val role: RolePermissionInfo? = null,
    )
}
