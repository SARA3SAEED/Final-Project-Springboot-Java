package com.PointOfSales.POS.Service.Impl;

import com.PointOfSales.POS.DTO.OrderDetailsRespDTO;
import com.PointOfSales.POS.Entity.OrderDetails;
import com.PointOfSales.POS.Entity.Product;
import com.PointOfSales.POS.Repository.OrderDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl {

    private final OrderDetailsRepo orderDetailsRepo;
    private final ProductServiceImpl productService;

    public OrderDetailsRespDTO calculateTotal(Integer barcode, Integer qty, String orderNo) {
        Product product = this.productService.getByBarcode(barcode);
        double total = product.getPrice() * qty;
        OrderDetails orderDetails = OrderDetails.builder()
                .price(product.getPrice())
                .qty(qty)
                .total(total)
                .productName(product.getProduct_name())
                .barcode(product.getBarcod())
                .orderNo(orderNo)
                .build();
        this.orderDetailsRepo.save(orderDetails);
        List<OrderDetails> orderDetailsList = this.orderDetailsRepo.findAllByOrderNo(orderNo);

        double finalTotal = orderDetailsList
                .stream()
                .mapToDouble(OrderDetails::getTotal)
                .sum();

        return OrderDetailsRespDTO.builder()
                .orderDetails(orderDetailsList)
                .total(finalTotal)
                .build();
    }

    public Double calOrderTotal(String orderNo) {
        return this.orderDetailsRepo.findAllByOrderNo(orderNo).
                stream()
                .mapToDouble(OrderDetails::getTotal)
                .sum();
    }


}
