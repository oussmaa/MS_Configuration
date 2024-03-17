package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Entity.Permissions;
import com.example.ms_configuration.Service.PermissionService;
import com.example.ms_configuration.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/PermissionRequest")
public class PermssionController {

    @Autowired
    private PermissionService permissionService;


    @GetMapping
    public ResponseEntity<List<Permissions>> getAllPermissions() {
        List<Permissions> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("getbyid/{permissionId}")
    public ResponseEntity<Permissions> getPermissionById(@PathVariable Long permissionId) {
        Permissions permission = permissionService.getPermissionById(permissionId);
        return ResponseEntity.ok(permission);
    }

    @PostMapping("/addpermissions")
    public ResponseEntity<Permissions> createPermission(@RequestBody Permissions permission) {
        Permissions createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok(createdPermission);
    }

    @PutMapping("/update/{permissionId}")
    public ResponseEntity<Permissions> updatePermission(@PathVariable Long permissionId,
                                                       @RequestBody Permissions updatedPermission) {
        Permissions permission = permissionService.updatePermission(permissionId, updatedPermission);
        return ResponseEntity.ok(permission);
    }

    @DeleteMapping("/delete/{permissionId}")
    public ResponseEntity<String> deletePermission(@PathVariable Long permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseEntity.ok("Permission deleted successfully");
    }


    @PutMapping("/asssignpermission/{roleId}")
    public ResponseEntity<String> assignPermissionsToRole(
            @PathVariable Long roleId,
            @RequestBody Set<Long> permissionIds) {
        permissionService.assignPermissionsToRole(roleId, permissionIds);
        return ResponseEntity.ok("Permissions assigned to role successfully");
    }

    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<String> removePermissionsFromRole(
            @PathVariable Long roleId,
            @RequestBody Set<Long> permissionIds) {
        permissionService.removePermissionsFromRole(roleId, permissionIds);
        return ResponseEntity.ok("Permissions removed from role successfully");
    }
}


