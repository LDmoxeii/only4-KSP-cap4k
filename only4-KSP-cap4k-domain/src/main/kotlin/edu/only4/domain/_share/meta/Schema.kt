package edu.only4.domain._share.meta

import jakarta.persistence.criteria.*
import org.hibernate.query.sqm.SortOrder
import org.hibernate.query.sqm.tree.domain.SqmBasicValuedSimplePath
import org.hibernate.query.sqm.tree.select.SqmSortSpecification

/**
 * 实体结构基类
 *
 * @author cap4j-ddd-codegen
 */
object Schema {

    fun interface Specification<E, S> {
        fun toPredicate(schema: S, criteriaQuery: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate?
    }

    fun interface SubqueryConfigure<E, S> {
        fun configure(subquery: Subquery<E>, schema: S)
    }

    /**
     * 表达式构建器
     */
    fun interface ExpressionBuilder<S, T> {
        fun build(schema: S): Expression<T>
    }

    /**
     * 断言构建器
     */
    fun interface PredicateBuilder<S> {
        fun build(schema: S): Predicate
    }

    /**
     * 排序构建器
     */
    fun interface OrderBuilder<S> {
        fun build(schema: S): Order
    }

    enum class JoinType {
        INNER,
        LEFT,
        RIGHT;

        fun toJpaJoinType(): jakarta.persistence.criteria.JoinType {
            return when (this) {
                INNER -> jakarta.persistence.criteria.JoinType.INNER
                LEFT -> jakarta.persistence.criteria.JoinType.LEFT
                RIGHT -> jakarta.persistence.criteria.JoinType.RIGHT
            }
        }
    }

    /**
     * 字段
     *
     * @param <T>
     */
    @Suppress("UNCHECKED_CAST")
    class Field<T> {
        private val path: Path<T>?
        private val criteriaBuilder: CriteriaBuilder?
        private val name: String?

        constructor(path: Path<T>, criteriaBuilder: CriteriaBuilder) {
            this.path = path
            this.criteriaBuilder = criteriaBuilder
            this.name = if (path is SqmBasicValuedSimplePath<*>) {
                path.navigablePath.localName
            } else null
        }

        constructor(name: String) {
            this.name = name
            this.path = null
            this.criteriaBuilder = null
        }

        protected fun _criteriaBuilder(): CriteriaBuilder? = criteriaBuilder

        fun path(): Path<T>? = path

        override fun toString(): String = name ?: ""

        fun asc(): Order = SqmSortSpecification(path as SqmBasicValuedSimplePath<T>, SortOrder.ASCENDING)

        fun desc(): Order = SqmSortSpecification(path as SqmBasicValuedSimplePath<T>, SortOrder.DESCENDING)

        fun isTrue(): Predicate = criteriaBuilder!!.isTrue(path as Expression<Boolean>)

        fun isFalse(): Predicate = criteriaBuilder!!.isFalse(path as Expression<Boolean>)

        fun isEmpty(): Predicate = criteriaBuilder!!.isEmpty(path as Expression<Collection<*>>)

        fun isNotEmpty(): Predicate = criteriaBuilder!!.isNotEmpty(path as Expression<Collection<*>>)

        fun equal(value: Any?): Predicate = criteriaBuilder!!.equal(path, value)

        fun equal(value: Expression<*>): Predicate = criteriaBuilder!!.equal(path, value)

        fun notEqual(value: Any?): Predicate = criteriaBuilder!!.notEqual(path, value)

        fun notEqual(value: Expression<*>): Predicate = criteriaBuilder!!.notEqual(path, value)

        fun isNull(): Predicate = criteriaBuilder!!.isNull(path)

        fun isNotNull(): Predicate = criteriaBuilder!!.isNotNull(path)

        fun <Y : Comparable<Y>> greaterThan(value: Y): Predicate =
            criteriaBuilder!!.greaterThan(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> greaterThan(value: Expression<out Y>): Predicate =
            criteriaBuilder!!.greaterThan(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> greaterThanOrEqualTo(value: Y): Predicate =
            criteriaBuilder!!.greaterThanOrEqualTo(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> greaterThanOrEqualTo(value: Expression<out Y>): Predicate =
            criteriaBuilder!!.greaterThanOrEqualTo(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> lessThan(value: Y): Predicate =
            criteriaBuilder!!.lessThan(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> lessThan(value: Expression<out Y>): Predicate =
            criteriaBuilder!!.lessThan(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> lessThanOrEqualTo(value: Y): Predicate =
            criteriaBuilder!!.lessThanOrEqualTo(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> lessThanOrEqualTo(value: Expression<out Y>): Predicate =
            criteriaBuilder!!.lessThanOrEqualTo(path as Expression<Y>, value)

        fun <Y : Comparable<Y>> between(value1: Y, value2: Y): Predicate =
            criteriaBuilder!!.between(path as Expression<Y>, value1, value2)

        fun <Y : Comparable<Y>> between(value1: Expression<out Y>, value2: Expression<out Y>): Predicate =
            criteriaBuilder!!.between(path as Expression<Y>, value1, value2)

        fun `in`(vararg values: Any?): Predicate = `in`(listOf(*values))

        fun `in`(values: Collection<*>): Predicate {
            val predicate = criteriaBuilder!!.`in`(path)
            values.forEach { value ->
                @Suppress("UNCHECKED_CAST")
                predicate.value(value as T)
            }
            return predicate
        }

        fun `in`(vararg expressions: Expression<*>): Predicate {
            val predicate = criteriaBuilder!!.`in`(path)
            expressions.forEach { expression ->
                @Suppress("UNCHECKED_CAST")
                predicate.value(expression as Expression<out T>)
            }
            return predicate
        }

        fun notIn(vararg values: Any?): Predicate = notIn(listOf(*values))

        fun notIn(values: Collection<*>): Predicate = criteriaBuilder!!.not(`in`(values))

        fun notIn(vararg expressions: Expression<*>): Predicate = criteriaBuilder!!.not(`in`(*expressions))

        fun like(value: String): Predicate = criteriaBuilder!!.like(path as Expression<String>, value)

        fun like(value: Expression<String>): Predicate = criteriaBuilder!!.like(path as Expression<String>, value)

        fun notLike(value: String): Predicate = criteriaBuilder!!.notLike(path as Expression<String>, value)

        fun notLike(value: Expression<String>): Predicate = criteriaBuilder!!.notLike(path as Expression<String>, value)

        // 简化方法名
        fun eq(value: Any?): Predicate = equal(value)

        fun eq(value: Expression<*>): Predicate = equal(value)

        fun neq(value: Any?): Predicate = notEqual(value)

        fun neq(value: Expression<*>): Predicate = notEqual(value)

        fun <Y : Comparable<Y>> gt(value: Y): Predicate = greaterThan(value)

        fun <Y : Comparable<Y>> gt(value: Expression<out Y>): Predicate = greaterThan(value)

        fun <Y : Comparable<Y>> ge(value: Y): Predicate = greaterThanOrEqualTo(value)

        fun <Y : Comparable<Y>> ge(value: Expression<out Y>): Predicate = greaterThanOrEqualTo(value)

        fun <Y : Comparable<Y>> lt(value: Y): Predicate = lessThan(value)

        fun <Y : Comparable<Y>> lt(value: Expression<out Y>): Predicate = lessThan(value)

        fun <Y : Comparable<Y>> le(value: Y): Predicate = lessThanOrEqualTo(value)

        fun <Y : Comparable<Y>> le(value: Expression<out Y>): Predicate = lessThanOrEqualTo(value)
    }
}
