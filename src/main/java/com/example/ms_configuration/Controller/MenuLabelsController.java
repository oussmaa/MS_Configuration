package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import com.example.ms_configuration.Service.MenuLabelsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MenuLabelRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuLabelsController {

    @Autowired
    private MenuLabelsService menuLabelsService;

    @PostMapping("/addlabelsandMenu/{menuId}")
    public ResponseEntity<String> addMenuLabel(@PathVariable Long menuId, @RequestBody MenuLabelsRequest menuLabelsRequest) {

        menuLabelsRequest.setIdMenu(menuId); // Set menuId in DTO
        menuLabelsService.addMenuLabelToMenu(menuLabelsRequest);
        return ResponseEntity.ok("Menu label added successfully");
    }

    @GetMapping("/getmenulabelbyid/{menuId}")
    @ApiOperation(value = "Retrieve a menu by ID", response = MenuRequest.class)
    public List<MenuLabelsRequest> getMenuById(@PathVariable Long menuId) {
        List<MenuLabelsRequest> menu = menuLabelsService.getMenuLabelById(menuId);
        return menu;
    }

    @DeleteMapping("/deletemenulabls/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        menuLabelsService.DeletMenuLabels(menuId);
        return ResponseEntity.ok("Menu deleted successfully");
    }


    @PutMapping("/updatemenulabls/{menuId}")
    public ResponseEntity<String> updateMenu(@PathVariable Long menuId, @RequestBody MenuLabelsRequest menuLabelsRequest) {
        menuLabelsRequest.setId(menuId);
        menuLabelsService.UpdateLabelsById(menuLabelsRequest);
        return ResponseEntity.ok("Menu updated successfully");
    }

}
