package edu.only4.adapter.portal.api.response

import edu.only4.application.queries.model.JRole
import org.babyfish.jimmer.client.FetchBy

class GetAllRolesResponse(
    val roles: List<@FetchBy("ROLE") JRole>,
)
