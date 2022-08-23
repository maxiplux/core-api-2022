package io.core.app.springbase2022.models.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.core.app.springbase2022.models.AuditModel;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;


    @JsonIgnoreProperties(value = {"categories", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Category category;




    @Min(value = 0)
    private Double quality;

    @Min(value = 1)
    private Double price;


    private String picture;

    public Double getPriceWithTaxes() {
        return this.price;
    }
}
