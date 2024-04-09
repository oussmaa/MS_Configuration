package com.example.ms_configuration.Request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Data
public class MenuRequest {

    private Long id;
    private Date defaultDate;

    private String ColorMenu ;

    private String NameMenu ;
    private List<MenuLabelsRequest> menuLabels;

}
