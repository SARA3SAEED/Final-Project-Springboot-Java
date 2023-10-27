package com.PointOfSales.POS.Controller;

import com.PointOfSales.POS.DTO.OrderDetailsRespDTO;
import com.PointOfSales.POS.Entity.Order;
import com.PointOfSales.POS.Entity.OrderStatus;
import com.PointOfSales.POS.Repository.OrderRepo;
import com.PointOfSales.POS.Service.Impl.OrderServiceImpl;
import com.PointOfSales.POS.util.payment.PaymentMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders/")
    public ResponseEntity<Order> createOrder() {
        try {
            Order order = new Order();
            String orderNo = UUID.randomUUID().toString();
            order.setOrderno(orderNo);
            order.setTotal(0d);
            order.setOrderStatus(OrderStatus.New);
            Order savedOrder = orderService.saveOrder(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate HTTP status code
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/complete/order/{orderNo}/{paymentMethod}")
    public ResponseEntity<Order> completeOrder(@PathVariable("orderNo") String orderNo,
                                               @PathVariable("paymentMethod") PaymentMethods paymentMethod) {
        try {
            Order savedOrder = orderService.completeOrder(orderNo, paymentMethod);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate HTTP status code
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId, @RequestBody Order updatedOrder) {
        try {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {

                existingOrder.setTotal(updatedOrder.getTotal());
                existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
                //   existingOrder.setOrderDetails(updatedOrder.getOrderDetails());


                Order savedOrder = orderService.saveOrder(existingOrder);
                return new ResponseEntity<>(savedOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        try {
            Order existingOrder = orderService.getOrderById(orderId);

            if (existingOrder != null) {
                orderService.deleteOrder(orderId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}