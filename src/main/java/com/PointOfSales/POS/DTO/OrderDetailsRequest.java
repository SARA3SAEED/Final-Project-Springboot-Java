package com.PointOfSales.POS.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsRequest {

    private Integer orderId;
    private Integer productId;
    private Integer qty;
    private Integer price;
}
