package com.example.ms_configuration.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuLabels> menuLabels;

    private Date DefaultDate = new Date();

    private String ColorMenu ;

    private String NameMenu ;
}
