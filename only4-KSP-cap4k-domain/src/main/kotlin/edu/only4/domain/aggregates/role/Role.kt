package edu.only4.domain.aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import jakarta.persistence.*
import jakarta.persistence.CascadeType
import jakarta.persistence.Table
import org.hibernate.annotations.*

@Aggregate(
    aggregate = "Role",
    name = "Role",
    root = true,
    type = Aggregate.TYPE_ENTITY,
    description = "角色表"
)
@Entity
@Table(name = "`role`")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update `role` set `del_flag` = 1 where `id` = ? ")
@Where(clause = "`del_flag` = 0")
class Role(
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "`role_id`")
    var rolePermissions: MutableList<RolePermission> = mutableListOf(),
    /**
     * ID
     * bigint
     */
    @Id
    @GeneratedValue(generator = "snowflake")
    @Column(name = "`id`")
    var id: Long? = null,

    /**
     * 角色名
     * varchar(255)
     */
    @NaturalId
    @Basic(optional = false)
    @Column(name = "`name`")
    var name: String = "",

    /**
     * 角色描述
     * varchar(255)
     */
    @Basic(optional = true)
    @Column(name = "`description`")
    var description: String? = null,
) {
    override fun equals(other: Any?) =
        this === other || (other is Role && name == other.name)

    override fun hashCode() = name.hashCode()
}
