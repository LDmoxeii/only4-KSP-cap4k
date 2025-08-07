package aggregates.role

import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Aggregate(aggregate = "Role", name = "Role", root = true, type = Aggregate.TYPE_ENTITY, description = "角色表")
@Entity
@Table(name = "`role`")
@DynamicInsert
@DynamicUpdate
class Role {
}
