package io.recruitment.assessment.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private Long quantity;
}
