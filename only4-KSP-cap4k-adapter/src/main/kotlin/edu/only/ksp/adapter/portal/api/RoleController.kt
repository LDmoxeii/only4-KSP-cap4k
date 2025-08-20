package edu.only.ksp.adapter.portal.api

import com.only4.cap4k.ddd.core.Mediator
import edu.only.ksp.adapter.portal.api.request.CreateRoleRequest
import edu.only.ksp.adapter.portal.api.request.GetRolesByConditionRequest
import edu.only.ksp.adapter.portal.api.request.UpdateRoleInfoRequest
import edu.only.ksp.adapter.portal.api.request.UpdateRolePermissionsRequest
import edu.only.ksp.application.commands.CreateRoleCmd
import edu.only.ksp.application.commands.DeleteRoleCmd
import edu.only.ksp.application.commands.UpdateRoleInfoCmd
import edu.only.ksp.application.commands.UpdateRolePermissionsCmd
import edu.only.ksp.application.queries.GetAllRolesQry
import edu.only.ksp.application.queries.GetRoleByIdQry
import edu.only.ksp.application.queries.GetRolePermissionsByIdQry
import edu.only.ksp.application.queries.GetRolesByConditionQry
import edu.only.ksp.application.queries._share.draft.JRole.RolePermissionInfo
import edu.only.ksp.application.queries._share.fetcher.RoleFetcher
import edu.only.ksp.application.queries._share.model.JRole
import edu.only.ksp.domain.aggregates.permission.Permission
import edu.only.ksp.domain.aggregates.role.RolePermission
import org.babyfish.jimmer.client.FetchBy
import org.babyfish.jimmer.client.meta.DefaultFetcherOwner
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@DefaultFetcherOwner(RoleFetcher::class)
@RequestMapping("role")
class RoleController {
    @PostMapping("test")
    fun test(): String {
        return "Hello, Role!"
    }


    @PostMapping("createRole")
    fun createRole(@RequestBody @Validated request: CreateRoleRequest) {
        val allPermissionCodes = Permission.getAllPermission()
        val permissionToBeAssigned = allPermissionCodes
            .filter { request.permissionCodes.contains(it.code) }
            .map {
                RolePermission(
                    permissionCode = it.code,
                )
            }

        Mediator.cmd.async(
            CreateRoleCmd.Request(
                name = request.name,
                description = request.description,
                permissions = permissionToBeAssigned.toMutableList()
            )
        )
    }

    @PostMapping("getAllRoles")
    fun getAllRoles(): List<@FetchBy("ROLE") JRole> {
        return Mediator.qry.send(GetAllRolesQry.Request()).roles
    }

    @PostMapping("getRolesByCondition")
    fun getRolesByCondition(@RequestBody request: GetRolesByConditionRequest): List<@FetchBy("ROLE") JRole> {
        return Mediator.qry.send(
            GetRolesByConditionQry.Request(
                name = request.name,
                description = request.description
            )
        ).roles
    }

    @PostMapping("getRoleById")
    fun getRoleById(@RequestBody request: Long): @FetchBy("ROLE") JRole? {
        return Mediator.qry.send(
            GetRoleByIdQry.Request(
                id = request
            )
        ).role
    }

    @PostMapping("getRolePermissionsById")
    fun getRolePermissionsById(@RequestBody request: Long): RolePermissionInfo? {

        val role = Mediator.qry.send(
            GetRolePermissionsByIdQry.Request(
                id = request
            )
        ).role

        val allPermission = Permission.getAllPermission()

        val rolePermissions = role?.rolePermissions?.associateBy { it.permissionCode }

        return role?.copy(allPermission.map { permission ->
            when (rolePermissions?.containsKey(permission.code) == true) {
                true -> rolePermissions[permission.code]!!.copy(permissionRemark = permission.remark)

                false -> RolePermissionInfo.TargetOf_rolePermissions(permission.code, permission.remark, false)
            }
        })
    }

    @PostMapping("updateRoleInfo")
    fun updateRoleInfo(@RequestBody @Validated request: UpdateRoleInfoRequest) {
        Mediator.cmd.async(
            UpdateRoleInfoCmd.Request(
                roleId = request.roleId,
                description = request.description
            )
        )
    }

    @PostMapping("updateRolePermissions")
    fun updateRolePermissions(@RequestBody @Validated request: UpdateRolePermissionsRequest) {
        val allPermission = Permission.getAllPermission()
        val permissionsToBeAssigned = allPermission
            .filter { request.permissionCodes.contains(it.code) }
            .map {
                RolePermission(
                    permissionCode = it.code,
                )
            }

        Mediator.cmd.async(
            UpdateRolePermissionsCmd.Request(
                roleId = request.roleId,
                permissions = permissionsToBeAssigned
            )
        )
    }

    @PostMapping("deleteRole")
    fun deleteRoleById(@RequestBody request: List<Long>) {
        Mediator.cmd.async(
            DeleteRoleCmd.Request(
                roleIds = request
            )
        )
    }
}
