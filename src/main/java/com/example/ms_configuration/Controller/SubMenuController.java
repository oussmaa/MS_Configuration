package com.example.ms_configuration.Controller;

import com.example.ms_configuration.Request.MenuLabelsRequest;
import com.example.ms_configuration.Request.MenuRequest;
import com.example.ms_configuration.Request.SubmenuRequest;
import com.example.ms_configuration.Service.SubMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SubMenuRequest")
@CrossOrigin(origins = "http://localhost:3000")
public class SubMenuController {


    @Autowired
    private SubMenuService subMenuService;

    @GetMapping("/getsubmenubyid/{menuId}")
    @ApiOperation(value = "Retrieve a menu by ID", response = MenuRequest.class)
    public List<SubmenuRequest> getSubMenuById(@PathVariable Long menuId) {
        List<SubmenuRequest> submenuRequests= subMenuService.getSubMenuById(menuId);
        return submenuRequests;
    }
    @PostMapping("/addsunMenul/{menuId}")
    public ResponseEntity<String> addsubmenulabel(@PathVariable Long menuId, @RequestBody SubmenuRequest submenuRequest) {
        submenuRequest.setIdlabelMenu(menuId); // Set menuId in DTO
         subMenuService.addSubmenutolabel(submenuRequest);
        return ResponseEntity.ok("Sub menu label added successfully");
    }
}
