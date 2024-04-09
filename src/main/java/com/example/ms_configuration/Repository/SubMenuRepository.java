package com.example.ms_configuration.Repository;

import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Entity.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {

    @Query("SELECT ml FROM SubMenu ml WHERE ml.menuLabels.id = :idmenu")
    List<SubMenu> findSubMenuByIdLabel(Long idmenu);



}
