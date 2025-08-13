package edu.only4.domain.aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import jakarta.persistence.*
import jakarta.persistence.CascadeType
import jakarta.persistence.Table
import org.hibernate.annotations.*
import java.time.LocalDateTime

@Aggregate(aggregate = "Role", name = "Role", root = true, type = Aggregate.TYPE_ENTITY, description = "角色表")
@Entity
@Table(name = "`role`")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update `role` set `del_flag` = 1 where `id` = ? ")
@Where(clause = "`del_flag` = 0")
class Role(
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "`role_id`", nullable = false)
    val rolePermissions: MutableList<RolePermission>,
    /**
     * ID
     * bigint
     */
    @Id
    @GeneratedValue(generator = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator")
    @GenericGenerator(
        name = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator",
        strategy = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator"
    )
    @Column(name = "`id`")
    var id: Long? = null,

    /**
     * 角色名
     * varchar(255)
     */
    @Column(name = "`name`")
    var name: String = "",

    /**
     * 角色描述
     * varchar(255)
     */
    @Column(name = "`description`")
    var description: String? = null,

    /**
     * 创建时间
     * timestamp
     */
    @Column(name = "`created_at`")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    /**
     * 逻辑删除
     * tinyint(1)
     */
    @Column(name = "`del_flag`")
    var delFlag: Boolean = false,
)
