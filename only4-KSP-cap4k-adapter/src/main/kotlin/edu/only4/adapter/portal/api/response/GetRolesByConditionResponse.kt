package edu.only4.adapter.portal.api.response

import edu.only4.application.queries.model.JRole
import org.babyfish.jimmer.client.FetchBy

class GetRolesByConditionResponse(
    val roles: List<@FetchBy("ROLE") JRole>,
)
