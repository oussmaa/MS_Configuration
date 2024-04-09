package com.example.ms_configuration.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "subMenu")
public class SubMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "menulabels_id")
    private MenuLabels menuLabels;

    private Date DefaultDate = new Date();
   private String icon ;
   private String title ;
   private String  pathname ;

   

 }
