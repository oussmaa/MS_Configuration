package com.example.ms_configuration.Request;

import lombok.Data;

import java.util.Date;

@Data
public class SubmenuRequest {

    private Long id;
    private Long  IdlabelMenu;
    private String icon ;
    private String title ;
    private String  pathname ;
    private Date defaultDate;
}
