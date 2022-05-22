package io.recruitment.assessment.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "default_image")
    private String defaultImage;

    @Column(name = "images")
    @ElementCollection
    private List<String> images;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;
}
