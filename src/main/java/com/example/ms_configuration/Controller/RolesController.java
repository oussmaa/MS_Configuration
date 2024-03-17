package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Entity.Roles;
import com.example.ms_configuration.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RolesRequest")
public class RolesController {



    @Autowired
    private RolesService roleService;

    @GetMapping("/getallroles")
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/getrolesbyid/{roleId}")
    public ResponseEntity<Roles> getRoleById(@PathVariable Long roleId) {
        Roles role = roleService.getRoleById(roleId);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/addroles")
    public ResponseEntity<Roles> createRole(@RequestBody Roles role) {
        Roles createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @PutMapping("/updateroles/{roleId}")
    public ResponseEntity<Roles> updateRole(@PathVariable Long roleId, @RequestBody Roles updatedRole) {
        Roles role = roleService.updateRole(roleId, updatedRole);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }
}




