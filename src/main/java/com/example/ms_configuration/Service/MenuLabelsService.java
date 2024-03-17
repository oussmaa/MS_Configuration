package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Menu;
import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Repository.MenuLabelRepository;
import com.example.ms_configuration.Repository.MenuRepository;
import com.example.ms_configuration.Request.MenuLabelsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MenuLabelsService {

    @Autowired
    private MenuLabelRepository menuLabelRepository;

    @Autowired
    private MenuRepository menuRepository;


    public void addMenuLabelToMenu(MenuLabelsRequest menuLabelsRequest) {
        MenuLabels menuLabel = convertToEntity(menuLabelsRequest);
        menuLabelRepository.save(menuLabel);
    }

    private MenuLabels convertToEntity(MenuLabelsRequest menuLabelsRequest) {
        MenuLabels menuLabel = new MenuLabels();
        Menu menu = menuRepository.findById(menuLabelsRequest.getIdMenu()).orElseThrow();
        menuLabel.setLabel(menuLabelsRequest.getLabel());
        menuLabel.setUrl(menuLabelsRequest.getUrl());
        menuLabel.setDefaultDate(new Date());
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
        menuLabels.setUrl(menuLabelsRequest.getUrl());
        menuLabels.setLabel(menuLabelsRequest.getLabel());
        menuLabelRepository.save(menuLabels);
        return menuLabelsRequest;

    }
}
