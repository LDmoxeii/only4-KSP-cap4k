package edu.only4.domain.aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.*


@Aggregate(
    aggregate = "Role",
    name = "RolePermission",
    root = false,
    type = Aggregate.TYPE_ENTITY,
    relevant = ["Role"],
    description = "角色权限表"
)
@Entity
@Table(name = "`role_permission`")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update `role_permission` set `del_flag` = 1 where `id` = ? ")
@Where(clause = "`del_flag` = 0")
class RolePermission(
    /**
     * ID
     * bigint
     */
    @Id
    @GeneratedValue(generator = "snowflake")
    @Column(name = "`id`")
    var id: Long? = null,

    /**
     * 权限编码
     * varchar(255)
     */
    @NaturalId
    @Basic(optional = false)
    @Column(name = "`permission_code`")
    var permissionCode: String = "",
) {
    override fun equals(other: Any?) =
        this === other || (other is RolePermission && permissionCode == other.permissionCode)

    override fun hashCode() = permissionCode.hashCode()
}
