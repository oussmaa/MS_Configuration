package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Menu;
import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Repository.MenuRepository;
import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuRequest> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();

        return menus.stream().map(this::convertToRequest).collect(Collectors.toList());
    }

    public MenuRequest getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        return convertToRequest(menu);
    }

    public void addMenu(MenuRequest menuRequest) {
        Menu menu = convertToEntity(menuRequest);
        menuRepository.save(menu);
    }

    private MenuRequest convertToRequest(Menu menu) {
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setId(menu.getId());
        menuRequest.setDefaultDate(menu.getDefaultDate());
        menuRequest.setColorMenu(menu.getColorMenu());
        menuRequest.setNameMenu(menu.getNameMenu());
        menuRequest.setMenuLabels(menu.getMenuLabels().stream()
                .map(this::convertToMenuLabelRequest)
                .collect(Collectors.toList()));
         return menuRequest;
    }

    private Menu convertToEntity(MenuRequest menuRequest) {
        Menu menu = new Menu();
        menu.setDefaultDate(new Date());
        menu.setNameMenu(menuRequest.getNameMenu());
        menu.setColorMenu(menuRequest.getColorMenu());

        if (menuRequest.getMenuLabels() != null ){

        List<MenuLabels> menuLabels = menuRequest.getMenuLabels().stream()
                .map(menuLabelDTO -> {
                    MenuLabels menuLabel = new MenuLabels();
                    menuLabel.setLabel(menuLabelDTO.getLabel());
                    menuLabel.setUrl(menuLabelDTO.getUrl());
                    menuLabel.setDefaultDate(menuLabelDTO.getDefaultDate());
                    menuLabel.setMenu(menu);
                    return menuLabel;
                })
                .collect(Collectors.toList());
        menu.setMenuLabels(menuLabels);

        }
        return menu;
    }

    private MenuLabelsRequest convertToMenuLabelRequest(MenuLabels menuLabel) {
        MenuLabelsRequest menuLabelsRequest = new MenuLabelsRequest();
        menuLabelsRequest.setLabel(menuLabel.getLabel());
        menuLabelsRequest.setUrl(menuLabel.getUrl());
        menuLabelsRequest.setDefaultDate(menuLabel.getDefaultDate());
        menuLabelsRequest.setId(menuLabel.getId());
        return menuLabelsRequest;
    }

    public void DeletMenu(Long Id)
    {
        menuRepository.deleteById(Id);
    }


    public MenuRequest UpdateLabelsById(MenuRequest menuRequest)
    {
        Menu menu = menuRepository.findById(menuRequest.getId()).orElseThrow();
        menu.setColorMenu(menuRequest.getColorMenu());
        menu.setNameMenu(menuRequest.getNameMenu());
        menuRepository.save(menu);
        return menuRequest;

    }


}
