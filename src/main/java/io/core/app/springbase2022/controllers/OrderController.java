package io.core.app.springbase2022.controllers;


import io.core.app.springbase2022.errors.ValidationErrorBuilder;

import io.core.app.springbase2022.models.dtos.CartDto;
import io.core.app.springbase2022.models.invoices.Invoice;
import io.core.app.springbase2022.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@RestController
@Secured({
        "ROLE_ADMIN",
        "ROLE_USER"
})
@RequestMapping("/api/v1/orders")
public class OrderController {
//https://phoenixnap.com/kb/spring-boot-validation-for-rest-services


    @Autowired
    private OrderService service;


    @GetMapping(value = "/")
    public ResponseEntity<?> findById(Principal principal) {

        Optional<Invoice> productOptional = service.findOrderByUser(principal);
        if (productOptional.isPresent()) {
            return new ResponseEntity<Invoice>(productOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Invoice>(productOptional.get(), HttpStatus.NOT_FOUND);
    }


//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
//    })
    @PostMapping
    public ResponseEntity<?> requestOrder(Principal principal,@Valid @RequestBody CartDto cartDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return new ResponseEntity<Invoice>(service.createOrder(principal,cartDto), HttpStatus.OK);

    }


}
