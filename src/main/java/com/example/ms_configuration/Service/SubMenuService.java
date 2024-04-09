package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Menu;
import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Entity.SubMenu;
import com.example.ms_configuration.Repository.MenuLabelRepository;
import com.example.ms_configuration.Repository.MenuRepository;
import com.example.ms_configuration.Repository.SubMenuRepository;
import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.SubmenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubMenuService {

    @Autowired
    private MenuLabelRepository menuLabelRepository;

    @Autowired
    private SubMenuRepository subMenuRepository;

    public List<SubmenuRequest> getSubMenuById(Long subid) {
        List<SubMenu> MenuLabels  = subMenuRepository.findSubMenuByIdLabel(subid);
        return convertToRequest(MenuLabels);
    }
    public void addSubmenutolabel(SubmenuRequest submenuRequest) {
        SubMenu sub = convertToEntity(submenuRequest);
        subMenuRepository.save(sub);
    }

    private SubMenu convertToEntity(SubmenuRequest submenuRequest) {
        SubMenu submenuLabel = new SubMenu();
        MenuLabels menulb = menuLabelRepository.findById(submenuRequest.getIdlabelMenu()).orElseThrow();
        submenuLabel.setTitle(submenuRequest.getTitle());
        submenuLabel.setIcon(submenuRequest.getIcon());
        submenuLabel.setDefaultDate(new Date());
        submenuLabel.setMenuLabels(menulb);
        submenuLabel.setPathname(submenuRequest.getPathname());
        return submenuLabel;
    }

    private List<SubmenuRequest>  convertToRequest(List<SubMenu> subMenus) {

            List<SubmenuRequest>   submenu= subMenus.stream().map(subMenu -> {

                    SubmenuRequest submenuRequest = new SubmenuRequest();
                        submenuRequest.setIcon(subMenu.getIcon());
                        submenuRequest.setPathname(subMenu.getPathname());
                        submenuRequest.setTitle(subMenu.getTitle());
                        submenuRequest.setDefaultDate(subMenu.getDefaultDate());
                        submenuRequest.setId(subMenu.getId());

                    return submenuRequest;

                }).
                collect(Collectors.toList());


        return submenu;
    }
}
