package edu.only4.application.queries.model


import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime


@Table(name = "role")
@Entity
interface JRole {

    @Id
    val id: Long

    val name: String

    val description: String?

    val createdAt: LocalDateTime

    @LogicalDeleted("true")
    val delFlag: Boolean

    @OneToMany(mappedBy = "role")
    val rolePermissions: List<JRolePermission>
}
