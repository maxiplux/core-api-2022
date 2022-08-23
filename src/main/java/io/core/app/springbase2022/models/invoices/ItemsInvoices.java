package io.core.app.springbase2022.models.invoices;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.core.app.springbase2022.models.AuditModel;
import io.core.app.springbase2022.models.items.Item;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder

public class ItemsInvoices  extends AuditModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties(value = {"invoice", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Invoice invoice;

    @JsonIgnoreProperties(value = {"item", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;


    private Double price;

    private  Double quantity;


    private Double tax;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemsInvoices)) return false;
        if (!super.equals(o)) return false;
        ItemsInvoices that = (ItemsInvoices) o;
        return getInvoice().equals(that.getInvoice()) &&
                getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem().hashCode());
    }
}
