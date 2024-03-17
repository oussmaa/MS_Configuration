package com.example.ms_configuration.Request;

import lombok.Data;

import java.util.Date;
@Data
public class MenuLabelsRequest {

    private Long id;
    private Long  IdMenu;
    private String label;
    private String url;
    private Date defaultDate;

}
