package com.PointOfSales.POS.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "DIGITAL_PRD")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DigitalProduct extends Product{
    @Column(name = "serial_no")
    private String serialNo;
    private String provider;
}
