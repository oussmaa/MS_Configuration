package com.example.ms_configuration.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date DefaultDate = new Date();
    private String code;

    @ManyToMany(mappedBy = "permissions")
    private List<Roles> roles ;
}
