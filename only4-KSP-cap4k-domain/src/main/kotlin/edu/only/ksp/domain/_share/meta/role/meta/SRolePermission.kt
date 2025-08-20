package edu.only.ksp.domain._share.meta.role.meta

import edu.only.ksp.domain._share.meta.Schema
import edu.only.ksp.domain.aggregates.role.RolePermission
import edu.only.ksp.domain.aggregates.role.RolePermission_
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate

/**
 * 角色权限表
 * 本文件由[cap4j-ddd-codegen-maven-plugin]生成
 * 警告：请勿手工修改该文件，重新生成会覆盖该文件
 * @author cap4j-ddd-codegen
 * @date 2025/08/10
 */
class SRolePermission(
    private val root: Path<RolePermission>,
    private val criteriaBuilder: CriteriaBuilder,
) {

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<RolePermission> = root

    /**
     * ID
     * bigint
     */
    fun id(): Schema.Field<Long> {
        return Schema.Field(root.get(RolePermission_.ID), criteriaBuilder)
    }

    /**
     * 权限编码
     * varchar(255)
     */
    fun permissionCode(): Schema.Field<String> {
        return Schema.Field(root.get(RolePermission_.PERMISSION_CODE), criteriaBuilder)
    }

    /**
     * 满足所有条件
     * @param restrictions
     * @return
     */
    fun all(vararg restrictions: Predicate): Predicate {
        return criteriaBuilder.and(*restrictions)
    }

    /**
     * 满足任一条件
     * @param restrictions
     * @return
     */
    fun any(vararg restrictions: Predicate): Predicate {
        return criteriaBuilder.or(*restrictions)
    }

    /**
     * 指定条件
     * @param builder
     * @return
     */
    fun spec(builder: Schema.PredicateBuilder<SRolePermission>): Predicate {
        return builder.build(this)
    }
}
