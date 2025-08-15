package edu.only4.domain.aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import jakarta.persistence.*
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where


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
    // 【行为方法开始】
    // 【行为方法结束】
    // 【字段映射开始】本段落由[cap4j-ddd-codegen-maven-plugin]维护，请不要手工改动
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
    @Basic(optional = false)
    @Column(name = "`permission_code`")
    var permissionCode: String = "",
)
