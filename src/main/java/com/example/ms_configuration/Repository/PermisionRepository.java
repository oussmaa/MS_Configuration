package com.example.ms_configuration.Repository;

import com.example.ms_configuration.Entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisionRepository extends JpaRepository<Permissions,Long> {
}
