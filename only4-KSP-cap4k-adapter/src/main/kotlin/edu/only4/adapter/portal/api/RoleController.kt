package edu.only4.adapter.portal.api

import com.only4.cap4k.ddd.core.Mediator
import edu.only4.adapter.portal.api.request.*
import edu.only4.adapter.portal.api.response.GetAllRolesResponse
import edu.only4.adapter.portal.api.response.GetRoleByIdResponse
import edu.only4.adapter.portal.api.response.GetRolePermissionsByIdResponse
import edu.only4.adapter.portal.api.response.GetRolesByConditionResponse
import edu.only4.adapter.portal.api.response.common.RolePermissionResponse
import edu.only4.application.commands.CreateRoleCmd
import edu.only4.application.commands.DeleteRoleCmd
import edu.only4.application.commands.UpdateRoleInfoCmd
import edu.only4.application.commands.UpdateRolePermissionsCmd
import edu.only4.application.queries.GetAllRolesQry
import edu.only4.application.queries.GetRoleByIdQry
import edu.only4.application.queries.GetRolePermissionsByIdQry
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
        return GetAllRolesResponse(roles = result)
    }

    @PostMapping("getRolesByCondition")
    fun getRolesByCondition(@RequestBody request: GetRolesByConditionRequest): GetRolesByConditionResponse {
        val result = Mediator.qry.send(
            GetRolesByConditionQry.Request(
                name = request.name,
                description = request.description
            )
        ).roles

        return GetRolesByConditionResponse(roles = result)
    }

    @PostMapping("getRoleById")
    fun getRoleById(@RequestBody request: GetRoleByIdRequest): GetRoleByIdResponse {
        val result = Mediator.qry.send(
            GetRoleByIdQry.Request(
                id = request.roleId
            )
        ).role

        return GetRoleByIdResponse(role = result)
    }

    @PostMapping("getRolePermissionsById")
    fun getRolePermissionsById(@RequestBody request: GetRolePermissionsByIdRequest): GetRolePermissionsByIdResponse {
        val role = Mediator.qry.send(
            GetRolePermissionsByIdQry.Request(
                id = request.roleId
            )
        ).role ?: throw IllegalArgumentException("Role with ID ${request.roleId} does not exist")

        val allPermission = Permission.getAllPermission()

        val permissionCodes = role.rolePermissions.map { it.permissionCode }.toSet()

        val result = allPermission.map { permissionCode ->

            when (permissionCodes.contains(permissionCode.code)) {
                true -> RolePermissionResponse(
                    permissionCode.code,
                    assigned = true
                )

                false -> RolePermissionResponse(
                    permissionCode.code,
                    assigned = false
                )
            }
        }

        return GetRolePermissionsByIdResponse(permissions = result)
    }

    @PostMapping("updateRoleInfo")
    fun updateRoleInfo(@RequestBody request: UpdateRoleInfoRequest) {
        Mediator.cmd.async(
            UpdateRoleInfoCmd.Request(
                roleId = request.roleId,
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
