package io.core.app.springbase2022.services.impl;

import io.core.app.springbase2022.models.dtos.CartDto;
import io.core.app.springbase2022.models.dtos.ItemDto;
import io.core.app.springbase2022.models.invoices.Invoice;
import io.core.app.springbase2022.models.invoices.ItemsInvoices;
import io.core.app.springbase2022.models.items.Item;
import io.core.app.springbase2022.models.users.User;
import io.core.app.springbase2022.repositories.InvoiceRepository;
import io.core.app.springbase2022.repositories.ItemInvoiceRepository;
import io.core.app.springbase2022.repositories.ItemRepository;
import io.core.app.springbase2022.repositories.UserRepository;
import io.core.app.springbase2022.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemInvoiceRepository itemInvoiceRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<Invoice> findOrderByUser(Principal principal) {
        return Optional.empty();
    }

    @Override
    public Invoice createOrder(Principal principal,CartDto cartDto) {

        User user=this.userRepository.findByUsername(principal.getName());
        Invoice invoice=this.invoiceRepository.save(Invoice.builder().comments(cartDto.getComments()).customer(user).description(cartDto.getDescription()).build());

        for (ItemDto item:cartDto.getItems() ) {
            var currentItem= Item.builder().id(item.getItemId()).build();
            var itemsInvoices= ItemsInvoices.builder().quantity(item.getQuantity()).invoice(invoice).item(currentItem).build();
            invoice.add(itemsInvoices);
            this.itemInvoiceRepository.save(itemsInvoices);
        }
        return this.invoiceRepository.save(invoice);
    }
}
