package io.core.app.springbase2022.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/protected/")
    public String helloWorldProtected(Principal principal) {
        return "Hello VIP " + principal.getName();
    }


}
