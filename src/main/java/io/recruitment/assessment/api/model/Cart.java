package io.recruitment.assessment.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_seq")
    @SequenceGenerator(name = "cart_id_seq", sequenceName = "cart_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;
}
