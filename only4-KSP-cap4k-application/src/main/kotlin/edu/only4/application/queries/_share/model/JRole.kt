package edu.only4.application.queries._share.model


import org.babyfish.jimmer.sql.*


@Table(name = "role")
@Entity
interface JRole {

    @Id
    val id: Long

    val name: String

    val description: String?

    @LogicalDeleted
    val delFlag: Long

    @OneToMany(mappedBy = "role")
    val rolePermissions: List<JRolePermission>
}
