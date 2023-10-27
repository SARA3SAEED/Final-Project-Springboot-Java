package com.PointOfSales.POS.Service.Impl;


import com.PointOfSales.POS.Entity.Order;
import com.PointOfSales.POS.Entity.OrderStatus;
import com.PointOfSales.POS.Repository.OrderRepo;
import com.PointOfSales.POS.Repository.ProductRepo;
import com.PointOfSales.POS.util.payment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderDetailsServiceImpl orderDetailsService;

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getOrderById(Integer orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }


    public void deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
    }


    public Order completeOrder(String orderNo, PaymentMethods paymentMethod) {
        Optional<Order> byOrderno = this.orderRepo.findByOrderno(orderNo);
        Double total = this.orderDetailsService.calOrderTotal(orderNo);
        PaymentFees payment = null;
        if (paymentMethod == PaymentMethods.CASH) {
             payment = new CashPayment();
        } else if (paymentMethod == PaymentMethods.CREDIT_CARD) {
             payment = new CreditCardPayment();
        }
        double totalWithFees = payment.calTotalWithFees(total, paymentMethod);

        byOrderno.get().setOrderStatus(OrderStatus.Completed);
        byOrderno.get().setTotal(totalWithFees);
        return this.orderRepo.save(byOrderno.get());
    }
}
