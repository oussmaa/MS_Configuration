package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import com.example.ms_configuration.Service.MenuLabelsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MenuLabelRequest")
public class MenuLabelsController {


    private MenuLabelsService menuLabelsService;

    @PostMapping("/{menuId}/addlabelsandMenu")
    public ResponseEntity<String> addMenuLabel(@PathVariable Long menuId, @RequestBody MenuLabelsRequest menuLabelsRequest) {

        menuLabelsRequest.setIdMenu(menuId); // Set menuId in DTO
        menuLabelsService.addMenuLabelToMenu(menuLabelsRequest);
        return ResponseEntity.ok("Menu label added successfully");
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
