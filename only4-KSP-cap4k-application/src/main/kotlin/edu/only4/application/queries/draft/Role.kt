package edu.only4.application.queries.draft

import edu.only4.application.queries.model.JRole
import edu.only4.application.queries.model.by
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

val SIMPLE_ROLE =
    newFetcher(JRole::class).by {
        allTableFields()
        name(false)
        rolePermissions {
            allScalarFields()
        }
    }
