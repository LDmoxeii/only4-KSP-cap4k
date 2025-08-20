package edu.only.ksp.application.queries._share.fetcher

import edu.only.ksp.application.queries._share.model.JRole
import edu.only.ksp.application.queries._share.model.by
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

object RoleFetcher {
    val ROLE =
        newFetcher(JRole::class).by {
            allScalarFields()
            rolePermissions {
                allScalarFields()
            }
        }
}
