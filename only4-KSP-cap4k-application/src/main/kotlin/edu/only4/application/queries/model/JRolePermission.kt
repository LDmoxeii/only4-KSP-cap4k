package edu.only4.application.queries.model

import org.babyfish.jimmer.sql.*

@Table(name = "role_permission")
@Entity
interface JRolePermission {

    @Id
    val id: Long

    val permissionCode: String

    val permissionRemark: String?

    @LogicalDeleted("true")
    val delFlag: Boolean

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: JRole
}
