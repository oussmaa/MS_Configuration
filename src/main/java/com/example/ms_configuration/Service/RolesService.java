package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Roles;
import com.example.ms_configuration.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository roleRepository;

    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    public Roles getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow();
    }

    public Roles createRole(Roles role) {
         return roleRepository.save(role);
    }

    public Roles updateRole(Long roleId, Roles updatedRole) {
        Roles role = roleRepository.findById(roleId)
                .orElseThrow();

        // Update the role fields with the values from updatedRole
        role.setRoles(updatedRole.getRoles());
        role.setDescrption(updatedRole.getDescrption());

        // Save the updated role
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
        Roles role = roleRepository.findById(roleId)
                .orElseThrow();
        roleRepository.delete(role);
    }

}
