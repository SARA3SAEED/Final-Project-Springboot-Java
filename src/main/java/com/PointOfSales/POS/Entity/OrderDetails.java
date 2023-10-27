package com.PointOfSales.POS.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orderDetails")
public class OrderDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Integer orderDetailsId;
    private Double price;
    private Double total;
    private Integer qty;
    private String orderNo;
    private String productName;
    private Integer barcode;

}
