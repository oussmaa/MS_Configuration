package com.example.ms_configuration.Request;

import com.example.ms_configuration.Entity.SubMenu;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuLabelsRequest {

    private Long id;
    private Long  IdMenu;
    private String icon ;
    private String title ;
    private Date defaultDate;
    private String  pathname ;
    private List<SubmenuRequest> subMenus;
}
