package com.example.ms_configuration.Repository;

import com.example.ms_configuration.Entity.MenuLabels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuLabelRepository extends JpaRepository<MenuLabels,Long> {
}
