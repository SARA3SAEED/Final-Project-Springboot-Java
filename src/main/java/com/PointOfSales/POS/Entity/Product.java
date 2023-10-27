package com.PointOfSales.POS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "PRODUCT_TYPE",
        discriminatorType = DiscriminatorType.STRING
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer product_id;
    @Column(name = "barcod")
    private Integer barcod;
    private String product_name;
    private String description;
    private Double price;
}
