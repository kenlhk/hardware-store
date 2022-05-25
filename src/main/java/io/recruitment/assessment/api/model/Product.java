package io.recruitment.assessment.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @CollectionTable(name = "product_image")
    private List<String> images;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;
}
