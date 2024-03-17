package com.example.ms_configuration.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "menu_labels")
public class MenuLabels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String url;
    private Date DefaultDate = new Date();
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;


}
