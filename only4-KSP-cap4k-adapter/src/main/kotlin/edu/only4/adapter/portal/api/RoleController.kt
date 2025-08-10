package edu.only4.adapter.portal.api

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.adapter.portal.api.request.*
import edu.only4.adapter.portal.api.response.GetAllRolesResponse
import edu.only4.adapter.portal.api.response.GetRoleByIdResponse
import edu.only4.adapter.portal.api.response.GetRolePermissionsByIdResponse
import edu.only4.adapter.portal.api.response.GetRolesByConditionResponse
import edu.only4.adapter.portal.api.response.common.RolePermissionResponse
import edu.only4.adapter.portal.api.response.common.RoleResponse
import edu.only4.application.commands.CreateRoleCmd
import edu.only4.application.commands.DeleteRoleCmd
import edu.only4.application.commands.UpdateRoleInfoCmd
import edu.only4.application.commands.UpdateRolePermissionsCmd
import edu.only4.application.queries.GetAllRolesQry
import edu.only4.application.queries.GetRoleByIdQry
import edu.only4.application.queries.GetRolesByConditionQry
import edu.only4.domain.aggregates.permission.Permission
import edu.only4.domain.aggregates.role.RolePermission
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("role")
class RoleController {
    @PostMapping("createRole")
    fun createRole(@RequestBody request: CreateRoleRequest) {
        val allPermissionCodes = Permission.getAllPermission()
        val permissionToBeAssigned = allPermissionCodes
            .filter { request.permissionCodes.contains(it.code) }
            .map {
                RolePermission(
                    permissionCode = it.code,
                    permissionRemark = it.remark,
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
    fun getAllRoles(): GetAllRolesResponse {
        val result = Mediator.qry.send(GetAllRolesQry.Request()).roles
            .map { role ->
                RoleResponse(
                    id = role.id ?: throw IllegalStateException("Role ID cannot be null"),
                    name = role.name,
                    description = role.description ?: "",
                    permissionCodes = role.rolePermissions.map { permission ->
                        RolePermissionResponse(
                            code = permission.permissionCode,
                            description = permission.permissionRemark ?: "",
                            assigned = true
                        )
                    }
                )
            }

        return GetAllRolesResponse(roles = result)
    }

    @PostMapping("getRolesByCondition")
    fun getRolesByCondition(@RequestBody request: GetRolesByConditionRequest): GetRolesByConditionResponse {
        val result = Mediator.qry.send(
            GetRolesByConditionQry.Request(
                name = request.name,
                description = request.description
            )
        )

        return GetRolesByConditionResponse(
            roles = result.roles.map { role ->
                RoleResponse(
                    id = role.id ?: throw IllegalStateException("Role ID cannot be null"),
                    name = role.name,
                    description = role.description ?: "",
                    permissionCodes = role.rolePermissions!!.map { permission ->
                        RolePermissionResponse(
                            code = permission.permissionCode,
                            description = permission.permissionRemark ?: "",
                            assigned = true
                        )
                    }
                )
            }
        )
    }

    @PostMapping("getRoleById")
    fun getRoleById(@RequestBody request: GetRoleByIdRequest): GetRoleByIdResponse {
        val result = Mediator.qry.send(
            GetRoleByIdQry.Request(
                id = request.roleId
            )
        )

        val role = result.role ?: throw IllegalArgumentException("Role with ID ${request.roleId} does not exist")

        return GetRoleByIdResponse(
            role = RoleResponse(
                id = role.id ?: throw IllegalStateException("Role ID cannot be null"),
                name = role.name,
                description = role.description ?: "",
                permissionCodes = role.rolePermissions!!.map { permission ->
                    RolePermissionResponse(
                        code = permission.permissionCode,
                        description = permission.permissionRemark ?: "",
                        assigned = true
                    )
                }
            )
        )
    }

    @PostMapping("getRolePermissionsById")
    fun getRolePermissionsById(@RequestBody request: GetRolePermissionsByIdRequest): GetRolePermissionsByIdResponse {
        val role = Mediator.qry.send(
            GetRoleByIdQry.Request(
                id = request.roleId
            )
        ).role ?: throw IllegalArgumentException("Role with ID ${request.roleId} does not exist")

        val permissionCodes = Permission.getAllPermission().map { it.code }.toSet()

        val result = role.rolePermissions!!.map { rolePermission ->
            RolePermissionResponse(
                code = rolePermission.permissionCode,
                description = rolePermission.permissionRemark ?: "",
                assigned = permissionCodes.contains(rolePermission.permissionCode)
            )
        }

        return GetRolePermissionsByIdResponse(permissions = result)
    }

    @PostMapping("updateRoleInfo")
    fun updateRoleInfo(@RequestBody request: UpdateRoleInfoRequest) {
        Mediator.cmd.async(
            UpdateRoleInfoCmd.Request(
                roleId = request.roleId,
                name = request.name,
                description = request.description
            )
        )
    }

    @PostMapping("updateRolePermissions")
    fun updateRolePermissions(@RequestBody request: UpdateRolePermissionsRequest) {
        val allPermission = Permission.getAllPermission()
        val permissionsToBeAssigned = allPermission
            .filter { request.permissionCodes.contains(it.code) }
            .map {
                RolePermission(
                    permissionCode = it.code,
                    permissionRemark = it.remark,
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
    fun deleteRoleById(@RequestBody request: DeleteRoleRequest) {
        Mediator.cmd.async(
            DeleteRoleCmd.Request(
                roleId = request.roleId
            )
        )
    }


}
