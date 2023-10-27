package com.PointOfSales.POS.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "PHYSICAL_PRD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhysicalProduct extends Product{
    private Double weight;
    private Double height;
}
