package edu.only4.domain._share.meta.role.meta

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.querydsl.QuerydslPredicate
import com.querydsl.core.types.OrderSpecifier
import edu.only4.domain._share.meta.Schema
import edu.only4.domain.aggregates.role.QRole
import edu.only4.domain.aggregates.role.Role
import edu.only4.domain.aggregates.role.Role_
import jakarta.persistence.criteria.*
import org.springframework.data.jpa.domain.Specification

/**
 * 角色表
 * 本文件由[cap4j-ddd-codegen-maven-plugin]生成
 * 警告：请勿手工修改该文件，重新生成会覆盖该文件
 * @author cap4j-ddd-codegen
 * @date 2025/08/10
 */
class SRole(
    private val root: Path<Role>,
    private val criteriaBuilder: CriteriaBuilder,
) {

    companion object {
        /**
         * 构建查询条件
         *
         * @param builder where条件构造器
         * @return
         */
        @JvmStatic
        fun specify(builder: Schema.PredicateBuilder<SRole>): Specification<Role> {
            return specify(builder, false, emptyList())
        }

        /**
         * 构建查询条件
         *
         * @param builder  where条件构造器
         * @param distinct 是否去重
         * @return
         */
        @JvmStatic
        fun specify(builder: Schema.PredicateBuilder<SRole>, distinct: Boolean): Specification<Role> {
            return specify(builder, distinct, emptyList())
        }

        /**
         * 构建查询条件
         *
         * @param builder       where条件构造器
         * @param orderBuilders 排序条件构造器
         * @return
         */
        @JvmStatic
        fun specify(
            builder: Schema.PredicateBuilder<SRole>,
            vararg orderBuilders: Schema.OrderBuilder<SRole>,
        ): Specification<Role> {
            return specify(builder, orderBuilders.toList())
        }

        /**
         * 构建查询条件
         *
         * @param builder       where条件构造器
         * @param orderBuilders 排序条件构造器
         * @return
         */
        @JvmStatic
        fun specify(
            builder: Schema.PredicateBuilder<SRole>,
            orderBuilders: List<Schema.OrderBuilder<SRole>>,
        ): Specification<Role> {
            return specify(builder, false, orderBuilders)
        }

        /**
         * 构建查询条件
         *
         * @param builder       where条件构造器
         * @param distinct      是否去重
         * @param orderBuilders 排序条件构造器
         * @return
         */
        @JvmStatic
        fun specify(
            builder: Schema.PredicateBuilder<SRole>,
            distinct: Boolean,
            vararg orderBuilders: Schema.OrderBuilder<SRole>,
        ): Specification<Role> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        /**
         * 构建查询条件
         *
         * @param builder       where条件构造器
         * @param distinct      是否去重
         * @param orderBuilders 排序条件构造器
         * @return
         */
        @JvmStatic
        fun specify(
            builder: Schema.PredicateBuilder<SRole>,
            distinct: Boolean,
            orderBuilders: List<Schema.OrderBuilder<SRole>>,
        ): Specification<Role> {
            return specify { schema, criteriaQuery, criteriaBuilder ->
                criteriaQuery.where(builder.build(schema))
                criteriaQuery.distinct(distinct)
                if (orderBuilders.isNotEmpty()) {
                    criteriaQuery.orderBy(orderBuilders.map { it.build(schema) })
                }
                null
            }
        }

        /**
         * 构建查询条件
         *
         * @param specifier 查询条件构造器
         * @return
         */
        @JvmStatic
        fun specify(specifier: Schema.Specification<Role, SRole>): Specification<Role> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val role = SRole(root, criteriaBuilder)
                specifier.toPredicate(role, criteriaQuery, criteriaBuilder)
            }
        }

        /**
         * 构建子查询
         *
         * @param resultClass      返回结果类型
         * @param selectBuilder    select条件构造器
         * @param predicateBuilder where条件构造器
         * @param criteriaBuilder
         * @param criteriaQuery
         * @param <E>
         * @return
         */
        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: Schema.ExpressionBuilder<SRole, E>,
            predicateBuilder: Schema.PredicateBuilder<SRole>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            return subquery(resultClass, { sq, role ->
                sq.select(selectBuilder.build(role))
                sq.where(predicateBuilder.build(role))
            }, criteriaBuilder, criteriaQuery)
        }

        /**
         * 构建子查询
         *
         * @param resultClass       返回结果类型
         * @param subqueryConfigure 子查询配置
         * @param criteriaBuilder
         * @param criteriaQuery
         * @param <E>
         * @return
         */
        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            subqueryConfigure: Schema.SubqueryConfigure<E, SRole>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(Role::class.java)
            val role = SRole(root, criteriaBuilder)
            subqueryConfigure.configure(sq, role)
            return sq
        }

        /**
         * 构建查询条件
         *
         * @param id 主键
         * @return
         */
        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<Role> {
            return JpaPredicate.byId(Role::class.java, id)
        }

        /**
         * 构建查询条件
         *
         * @param ids 主键
         * @return
         */
        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<Role> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(Role::class.java, ids as Iterable<Any>)
        }

        /**
         * 构建查询条件
         *
         * @param ids 主键
         * @return
         */
        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<Role> {
            return JpaPredicate.byIds(Role::class.java, ids.toList())
        }

        /**
         * 构建查询条件
         *
         * @param builder 查询条件构造器
         * @return
         */
        @JvmStatic
        fun predicate(builder: Schema.PredicateBuilder<SRole>): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder))
        }

        /**
         * 构建查询条件
         *
         * @param builder  查询条件构造器
         * @param distinct 是否去重
         * @return
         */
        @JvmStatic
        fun predicate(builder: Schema.PredicateBuilder<SRole>, distinct: Boolean): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder, distinct))
        }

        /**
         * 构建查询条件
         *
         * @param builder       查询条件构造器
         * @param orderBuilders 排序构造器
         * @return
         */
        @JvmStatic
        fun predicate(
            builder: Schema.PredicateBuilder<SRole>,
            orderBuilders: List<Schema.OrderBuilder<SRole>>,
        ): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder, false, orderBuilders))
        }

        /**
         * 构建查询条件
         *
         * @param builder       查询条件构造器
         * @param orderBuilders 排序构造器
         * @return
         */
        @JvmStatic
        fun predicate(
            builder: Schema.PredicateBuilder<SRole>,
            vararg orderBuilders: Schema.OrderBuilder<SRole>,
        ): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder, false, *orderBuilders))
        }

        /**
         * 构建查询条件
         *
         * @param builder       查询条件构造器
         * @param distinct      是否去重
         * @param orderBuilders 排序构造器
         * @return
         */
        @JvmStatic
        fun predicate(
            builder: Schema.PredicateBuilder<SRole>,
            distinct: Boolean,
            orderBuilders: List<Schema.OrderBuilder<SRole>>,
        ): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder, distinct, orderBuilders))
        }

        /**
         * 构建查询条件
         *
         * @param builder       查询条件构造器
         * @param distinct      是否去重
         * @param orderBuilders 排序构造器
         * @return
         */
        @JvmStatic
        fun predicate(
            builder: Schema.PredicateBuilder<SRole>,
            distinct: Boolean,
            vararg orderBuilders: Schema.OrderBuilder<SRole>,
        ): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(builder, distinct, *orderBuilders))
        }

        /**
         * 构建查询条件
         *
         * @param specifier 查询条件构造器
         * @return
         */
        @JvmStatic
        fun predicate(specifier: Schema.Specification<Role, SRole>): JpaPredicate<Role> {
            return JpaPredicate.bySpecification(Role::class.java, specify(specifier))
        }

        /**
         * 构建querydsl查询条件
         *
         * @param filterBuilder          查询条件构造器
         * @param orderSpecifierBuilders 排序构造器
         * @return
         */
        @JvmStatic
        fun querydsl(
            filterBuilder: java.util.function.Function<QRole, com.querydsl.core.types.Predicate>,
            vararg orderSpecifierBuilders: java.util.function.Function<QRole, OrderSpecifier<*>>,
        ): QuerydslPredicate<Role> {
            return QuerydslPredicate.of(Role::class.java)
                .where(filterBuilder.apply(QRole.role))
                .orderBy(*orderSpecifierBuilders.map { it.apply(QRole.role) }.toTypedArray())
        }

        /**
         * 构建querydsl查询条件
         *
         * @param filter          查询条件构造器
         * @param orderSpecifiers 排序构造器
         * @return
         */
        @JvmStatic
        fun querydsl(
            filter: com.querydsl.core.types.Predicate,
            vararg orderSpecifiers: OrderSpecifier<*>,
        ): QuerydslPredicate<Role> {
            return QuerydslPredicate.of(Role::class.java)
                .where(filter)
                .orderBy(*orderSpecifiers)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<Role> = root

    /**
     * ID
     * bigint
     */
    fun id(): Schema.Field<Long> {
        return Schema.Field(root.get(Role_.ID), criteriaBuilder)
    }

    /**
     * 角色名
     * varchar(255)
     */
    fun name(): Schema.Field<String> {
        return Schema.Field(root.get(Role_.NAME), criteriaBuilder)
    }

    /**
     * 角色描述
     * varchar(255)
     */
    fun description(): Schema.Field<String> {
        return Schema.Field(root.get(Role_.DESCRIPTION), criteriaBuilder)
    }

    fun rolePermissions(): Schema.Field<List<edu.only4.domain.aggregates.role.RolePermission>> {
        return Schema.Field(root.get(Role_.ROLE_PERMISSIONS), criteriaBuilder)
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
    fun spec(builder: Schema.PredicateBuilder<SRole>): Predicate {
        return builder.build(this)
    }

    /**
     * RolePermission 关联查询条件定义
     *
     * @param joinType
     * @return
     */
    fun joinRolePermission(joinType: Schema.JoinType): SRolePermission {
        val type = joinType.toJpaJoinType()

        @Suppress("UNCHECKED_CAST")
        val join =
            (root as Root<Role>).join<Role, edu.only4.domain.aggregates.role.RolePermission>("rolePermissions", type)
        return SRolePermission(join, criteriaBuilder)
    }
}
