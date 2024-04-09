package com.example.ms_configuration.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "menulabels")
public class MenuLabels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon ;
    private String title ;
    private Date DefaultDate = new Date();
    private String  pathname ;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuLabels", cascade = CascadeType.ALL)
    private List<SubMenu> subMenus;


}
