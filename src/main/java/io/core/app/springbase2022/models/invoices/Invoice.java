package io.core.app.springbase2022.models.invoices;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.core.app.springbase2022.models.AuditModel;
import io.core.app.springbase2022.models.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data

@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Invoice extends AuditModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String comments;

    @JsonIgnoreProperties(value = {"invoices", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User customer;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Set<ItemsInvoices> items = new HashSet<>();

    public void add(List<ItemsInvoices> items) {
        this.items.addAll(items);
    }
    public void add(ItemsInvoices item) {
        if(this.items==null) {this.items = new HashSet<>();}
        this.items.add(item);
    }


}
