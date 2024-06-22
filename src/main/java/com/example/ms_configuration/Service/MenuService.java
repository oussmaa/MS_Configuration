package com.example.ms_configuration.Service;

import com.example.ms_configuration.Entity.Menu;
import com.example.ms_configuration.Entity.MenuLabels;
import com.example.ms_configuration.Entity.SubMenu;
import com.example.ms_configuration.Repository.MenuLabelRepository;
import com.example.ms_configuration.Repository.MenuRepository;
import com.example.ms_configuration.Repository.SubMenuRepository;
import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import com.example.ms_configuration.Request.SubmenuRequest;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private SubMenuRepository subMenuRepository;

    @Autowired
    private MenuLabelRepository menuLabelRepository;
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
                    menuLabel.setTitle(menuLabelDTO.getTitle());
                    menuLabel.setIcon(menuLabelDTO.getIcon());
                    menuLabel.setDefaultDate(menuLabelDTO.getDefaultDate());
                    menuLabel.setMenu(menu);

                    List<SubMenu> suber=  menuLabelDTO.getSubMenus().stream().map(submenu ->{
                        SubMenu sub = new SubMenu();
                        sub.setTitle(submenu.getTitle());
                        sub.setIcon(submenu.getIcon());
                        sub.setDefaultDate(submenu.getDefaultDate());
                        sub.setPathname(submenu.getPathname());
                        sub.setMenuLabels(menuLabel);

                        return sub;

                    }).collect(Collectors.toList());

                    menuLabel.setSubMenus(suber);

                    return menuLabel;
                })
                .collect(Collectors.toList());

        menu.setMenuLabels(menuLabels);

        }


        return menu;
    }

    private MenuLabelsRequest convertToMenuLabelRequest(MenuLabels menuLabel) {
        MenuLabelsRequest menuLabelsRequest = new MenuLabelsRequest();
        menuLabelsRequest.setIcon(menuLabel.getIcon());
        menuLabelsRequest.setTitle(menuLabel.getTitle());
        menuLabelsRequest.setDefaultDate(menuLabel.getDefaultDate());
        menuLabelsRequest.setId(menuLabel.getId());
        menuLabelsRequest.setPathname(menuLabel.getPathname());
        menuLabelsRequest.setSubMenus(menuLabel.getSubMenus().stream()
                .map(this::conveSubmenutoRequest)
                .collect(Collectors.toList()));
        return menuLabelsRequest;
    }
    private SubmenuRequest conveSubmenutoRequest(SubMenu subMenu) {
        SubmenuRequest submenuRequest = new SubmenuRequest();
        submenuRequest.setIcon(subMenu.getIcon());
        submenuRequest.setTitle(subMenu.getTitle());
        submenuRequest.setDefaultDate(subMenu.getDefaultDate());
        submenuRequest.setId(subMenu.getId());
        submenuRequest.setPathname(subMenu.getPathname());
        return submenuRequest;
    }
public void deleteListMenulabel(List<MenuLabels> list)
{
 for(int i=0; i< list.size();i++)
 {
     List<SubMenu> sub = subMenuRepository.findSubMenuByIdLabel(list.get(i).getId()) ;
if (sub!=null)
{
    deleteListSubmenu(sub);
}
     menuLabelRepository.deleteById(list.get(i).getId());
 }


}
public void deleteListSubmenu(List<SubMenu> list)
{
    for(int i=0; i< list.size();i++)
    {
        List<SubMenu> sub = subMenuRepository.findSubMenuByIdLabel(list.get(i).getId()) ;

        menuLabelRepository.deleteById(list.get(i).getId());
    }


}
    public String DeletMenu(Long Id)
    {
        long idmenu=0;
        try {

           Optional<Menu> menu =   menuRepository.findById(Id);
            idmenu = menu.get().getId();
          if (menu.isPresent())
          {
              menuRepository.deleteById(Id);

              List<MenuLabels> menuLabels =   menuLabelRepository.findMenuLabelsByIdMenu(idmenu);

              if(menuLabels!=null)
              {
                  deleteListMenulabel(menuLabels);
              }

           }




        }catch (Exception e)
        {
            return e.getMessage().toString();
        }

        return null;
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
