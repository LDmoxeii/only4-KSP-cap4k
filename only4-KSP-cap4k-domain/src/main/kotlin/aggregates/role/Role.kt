package aggregates.role

import aggregates.role.events.UpdatedRoleInfoDomainEvent
import aggregates.role.events.UpdatedRolePermissionsDomainEvent
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
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

    // 【行为方法结束】
    // 【字段映射开始】本段落由[cap4j-ddd-codegen-maven-plugin]维护，请不要手工改动
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "`role_id`", nullable = false)
    private val rolePermissions: MutableList<RolePermission> = mutableListOf(),
) {
    fun updateRoleInfo(name: String, description: String?) {
        this.name = name
        this.description = description
        events().attach(this) { UpdatedRoleInfoDomainEvent(this) }
    }

    fun updateRolePermission(newPermissions: List<RolePermission>) {
        val currentPermissionMap = rolePermissions.associateBy { it.permissionCode }
        val newPermissionMap = newPermissions.associateBy { it.permissionCode }

        // 移除不存在于新权限列表中的旧权限
        val toRemove = currentPermissionMap.keys - newPermissionMap.keys
        rolePermissions.removeAll { it.permissionCode in toRemove }

        // 添加新权限
        val toAdd = newPermissionMap.keys - currentPermissionMap.keys
        rolePermissions.addAll(toAdd.mapNotNull { newPermissionMap[it] })

        events().attach(this) { UpdatedRolePermissionsDomainEvent(this) }
    }
}
