package edu.only.ksp.application.validater

import com.only4.cap4k.ddd.core.Mediator
import edu.only.ksp.application.queries.RoleExistsByNameQry
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Constraint(validatedBy = [UniqueRoleName.Validator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class UniqueRoleName(
    val message: String = "已存在同名角色",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
) {
    class Validator : ConstraintValidator<UniqueRoleName, String> {
        override fun isValid(
            value: String,
            context: ConstraintValidatorContext,
        ): Boolean {
            return !Mediator.queries.send(
                RoleExistsByNameQry.Request(
                    value
                )
            ).exists
        }
    }
}
