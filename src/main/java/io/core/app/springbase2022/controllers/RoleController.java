package io.core.app.springbase2022.controllers;

import io.core.app.springbase2022.controllers.generics.CrudController;
import io.core.app.springbase2022.models.users.Role;
import io.core.app.springbase2022.services.RoleServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/roles")
@Data
public class RoleController extends CrudController<Role> {

    @Autowired
    private RoleServices roleServices;

    @PostConstruct
    public void posContructor() {
        this.service = roleServices;
    }

}
