package edu.only4.application.queries._share.model

import org.babyfish.jimmer.sql.*

@Table(name = "role_permission")
@Entity
interface JRolePermission {

    @Id
    val id: Long

    val permissionCode: String

    @LogicalDeleted("true")
    val delFlag: Boolean

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: JRole
}
