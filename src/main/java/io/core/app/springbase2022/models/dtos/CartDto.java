package io.core.app.springbase2022.models.dtos;

import io.core.app.springbase2022.models.invoices.ItemsInvoices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private String description;
    private String comments;

    private Set<ItemDto> items;
}
