package io.core.app.springbase2022.services;



import io.core.app.springbase2022.models.dtos.CartDto;
import io.core.app.springbase2022.models.invoices.Invoice;

import java.security.Principal;
import java.util.Optional;

public interface OrderService {





    Optional<Invoice> findOrderByUser(Principal principal);

    Invoice createOrder(Principal principal,CartDto cartDto);
}
