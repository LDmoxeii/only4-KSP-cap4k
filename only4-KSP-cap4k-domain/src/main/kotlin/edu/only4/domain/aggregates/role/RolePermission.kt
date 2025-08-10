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
    // 【行为方法开始】
    // 【行为方法结束】
    // 【字段映射开始】本段落由[cap4j-ddd-codegen-maven-plugin]维护，请不要手工改动
    /**
     * ID
     * bigint
     */
    @Id @GeneratedValue(generator = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator") @GenericGenerator(
        name = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator",
        strategy = "com.only4.cap4k.ddd.domain.distributed.SnowflakeIdentifierGenerator"
    ) @Column(name = "`id`")
    var id: Long? = null,

    /**
     * 权限编码
     * varchar(255)
     */
    @Column(name = "`permission_code`")
    var permissionCode: String = "",

    /**
     * 权限备注
     * varchar(255)
     */
    @Column(name = "`permission_remark`")
    var permissionRemark: String? = null,

    /**
     * 逻辑删除
     * tinyint(1)
     */
    @Column(name = "`del_flag`")
    var delFlag: Boolean = false,
)
