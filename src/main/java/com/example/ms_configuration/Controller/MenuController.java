package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import com.example.ms_configuration.Service.MenuLabelsService;
import com.example.ms_configuration.Service.MenuService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/menurequest")
@CrossOrigin(origins = "http://localhost:3000")
//@Api(value = "Menu API", description = "CRUD operations for Menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @Autowired
    private MenuLabelsService menuLabelsService;

    // Endpoint for getting all menus
    @GetMapping("/getallmenu")
   // @ApiOperation(value = "Retrieve all menu", response = List.class)
    public ResponseEntity<List<MenuRequest>> getAllMenus() {
        List<MenuRequest> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    // Endpoint for getting a menu by ID
    @GetMapping("/getmenubyid/{menuId}")
   // @ApiOperation(value = "Retrieve a menu by ID", response = MenuRequest.class)
    public ResponseEntity<MenuRequest> getMenuById(@PathVariable Long menuId) {
        MenuRequest menu = menuService.getMenuById(menuId);
        return ResponseEntity.ok(menu);
    }

    // Endpoint for adding a new menu
    @PostMapping("/addmenu")
   // @ApiOperation(value = "Create a new person")
    public ResponseEntity<String> addMenu(@RequestBody MenuRequest menuRequest) {
        menuService.addMenu(menuRequest);
        return ResponseEntity.ok("Menu added successfully");
    }

    @DeleteMapping("/{menuId}")
   // @ApiOperation(value = "Delete a menu by ID")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        menuService.DeletMenu(menuId);
        return ResponseEntity.ok("Menu deleted successfully");
    }


    @PutMapping("/updatemenu/{menuId}")
   // @ApiOperation(value = "Update a menu by ID")
    public ResponseEntity<String> updateMenu(@PathVariable Long menuId, @RequestBody MenuRequest menuRequest) {
        menuRequest.setId(menuId);
        menuService.UpdateLabelsById(menuRequest);
        return ResponseEntity.ok("Menu updated successfully");
    }
}
