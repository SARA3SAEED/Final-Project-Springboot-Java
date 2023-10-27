package com.PointOfSales.POS.Controller;

import com.PointOfSales.POS.DTO.OrderDetailsRespDTO;
import com.PointOfSales.POS.Service.Impl.OrderDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-details")
public class OrderDetailsController {
    private final OrderDetailsServiceImpl orderDetailsService;

    @GetMapping("/add-to-cart/barcode/{barcode}/qty/{qty}/orderNo/{orderNo}")
    public OrderDetailsRespDTO addToCart(@PathVariable("barcode") Integer barcode,
                                         @PathVariable("orderNo") String orderNo,
                                         @PathVariable("qty") int qty) {
        return this.orderDetailsService.calculateTotal(barcode, qty, orderNo);
    }

}
