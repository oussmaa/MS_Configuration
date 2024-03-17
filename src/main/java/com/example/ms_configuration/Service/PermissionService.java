package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Permissions;
import com.example.ms_configuration.Entity.Roles;
import com.example.ms_configuration.Repository.PermisionRepository;
import com.example.ms_configuration.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {


    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private PermisionRepository permissionRepository;

    public void assignPermissionsToRole(Long roleId, Set<Long> permissionIds) {
        Roles role = roleRepository.findById(roleId).orElseThrow();

        List<Permissions> permissions = permissionRepository.findAllById(permissionIds);
        role.setPermissions(permissions);

        roleRepository.save(role);
    }

    public void removePermissionsFromRole(Long roleId, Set<Long> permissionIds) {
        Roles role = roleRepository.findById(roleId).orElseThrow();
        List<Permissions> permissions = permissionRepository.findAllById(permissionIds);
        role.getPermissions().removeAll(permissions);
        roleRepository.save(role);
    }

    public List<Permissions> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permissions getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId).orElseThrow();
    }

    public Permissions createPermission(Permissions permission) {
         return permissionRepository.save(permission);
    }

    public Permissions updatePermission(Long permissionId, Permissions updatedPermission) {
        Permissions permission = permissionRepository.findById(permissionId).orElseThrow();
        permission.setCode(updatedPermission.getCode());
        return permissionRepository.save(permission);
    }

    public void deletePermission(Long permissionId) {
        Permissions permission = permissionRepository.findById(permissionId).orElseThrow();
        permissionRepository.delete(permission);
    }
}


