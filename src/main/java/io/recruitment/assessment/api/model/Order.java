package io.recruitment.assessment.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
