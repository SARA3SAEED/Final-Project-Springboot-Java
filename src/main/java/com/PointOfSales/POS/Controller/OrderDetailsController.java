package com.PointOfSales.POS.Controller;

import com.PointOfSales.POS.DTO.OrderDetailsRespDTO;
import com.PointOfSales.POS.Service.Impl.OrderDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/delete/{orderDetailsId}")
    public ResponseEntity<String> deleteOrderDetails(@PathVariable Integer orderDetailsId) {
        try {
            String deletionResult = orderDetailsService.deleteOrderDetails(orderDetailsId);
            if (deletionResult.startsWith("OrderDetails deleted")) {
                return new ResponseEntity<>(deletionResult, HttpStatus.OK);
            } else if (deletionResult.equals("OrderDetails not found")) {
                return new ResponseEntity<>(deletionResult, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(deletionResult, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Handle exceptions and return an appropriate HTTP status code
            return new ResponseEntity<>("Error deleting OrderDetails", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
