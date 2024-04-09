package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Menu;
import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Repository.MenuLabelRepository;
import com.example.ms_configuration.Repository.MenuRepository;
import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuLabelsService {

    @Autowired
    private MenuLabelRepository menuLabelRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuLabelsRequest> getMenuLabelById(Long menuId) {
       List<MenuLabels> MenuLabels  = menuLabelRepository.findMenuLabelsByIdMenu(menuId);
        return convertToRequest(MenuLabels);
    }


    private List<MenuLabelsRequest>  convertToRequest(List<MenuLabels> menuLabels) {

        List<MenuLabelsRequest>   menuLabelsReques= menuLabels.stream().map(menuLabel -> {

            MenuLabelsRequest menuLabelsRequest = new MenuLabelsRequest();
            menuLabelsRequest.setIcon(menuLabel.getIcon());
                    menuLabelsRequest.setTitle(menuLabel.getTitle());
                    menuLabelsRequest.setDefaultDate(menuLabel.getDefaultDate());
                    menuLabelsRequest.setId(menuLabel.getId());
                    menuLabelsRequest.setPathname(menuLabel.getPathname());

        return menuLabelsRequest;

        }).
        collect(Collectors.toList());




        return menuLabelsReques;
    }
    public void addMenuLabelToMenu(MenuLabelsRequest menuLabelsRequest) {
        MenuLabels menuLabel = convertToEntity(menuLabelsRequest);
        menuLabelRepository.save(menuLabel);
    }

    private MenuLabels convertToEntity(MenuLabelsRequest menuLabelsRequest) {
        MenuLabels menuLabel = new MenuLabels();
        Menu menu = menuRepository.findById(menuLabelsRequest.getIdMenu()).orElseThrow();
        menuLabel.setTitle(menuLabelsRequest.getTitle());
        menuLabel.setIcon(menuLabelsRequest.getIcon());
        menuLabel.setDefaultDate(new Date());
        menuLabel.setPathname(menuLabelsRequest.getPathname());
        menuLabel.setMenu(menu);
        return menuLabel;
    }


    public void DeletMenuLabels(Long Id)
    {
        menuLabelRepository.deleteById(Id);
    }


    public MenuLabelsRequest UpdateLabelsById(MenuLabelsRequest menuLabelsRequest)
    {
        MenuLabels menuLabels = menuLabelRepository.findById(menuLabelsRequest.getId()).orElseThrow();
        menuLabels.setIcon(menuLabelsRequest.getIcon());
        menuLabels.setTitle(menuLabelsRequest.getTitle());
        menuLabelRepository.save(menuLabels);
        return menuLabelsRequest;

    }

}
