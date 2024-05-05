package com.example.CompanyB.CustomerOrderMnaagementModule.Service;

import java.lang.reflect.Field;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import com.example.CompanyB.CustomerOrderMnaagementModule.Model.DeliveryInfoC;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.OrderInfoC;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.OrderModel;
import com.example.CompanyB.CustomerOrderMnaagementModule.Repository.OrderRepository;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    
    // Getting the all orders
    public List<OrderModel> allOrders() {
        return orderRepository.findAll();

    }

    // Getting the order by order id
    public Optional<OrderModel> singleOrder(Long orderID) {
        return orderRepository.findOrderByOrderID(orderID);
    }

    // Getting the all orders of a customer
    public List<OrderModel> allCustomerOrders(String customerID) {
        return orderRepository.findOrderByCustomerID(customerID);

    }



    

    // Updating the order status
    public OrderModel updateOrderStatus(OrderModel existingOrder) {
        if (existingOrder.isPaymentDone() == true) {
            System.out.println("Order is confirmed");
            existingOrder.setManufactureDone("In Progress");
        } else {
            existingOrder.setManufactureDone(null);
        }
        if (existingOrder.getManufactureDone() == "Completed") {
            System.out.println("Order is ready");
            existingOrder.setDeliveryStatus("Shipped");
        } else {
            existingOrder.setDeliveryStatus(null);
        }

        return existingOrder;

    }
    
    // Updating the order info
    @SuppressWarnings("null")
    public OrderModel updateOrderInfo(Long orderID, OrderInfoC orderInfo) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "orderInfo");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(),orderInfo);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }
    
    

    // Updating the simaualtion staus
    // @SuppressWarnings("null")
    @SuppressWarnings("null")
    public OrderModel updateSimulationStatus(Long orderID, Boolean simulationStatus) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "simulationStatus");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), simulationStatus);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

    // Updating the availability of the parts
    // @SuppressWarnings("null")
    @SuppressWarnings("null")
    public OrderModel updatePartsAvailable(Long orderID, Boolean partsAvailable) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "partsAvailable");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), partsAvailable);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

    // Updating the total price of the order
    @SuppressWarnings("null")
    public OrderModel updatePayment(Long orderID, double payment) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "payment");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), payment);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

    // Updating the delivery info
    @SuppressWarnings("null")
    public OrderModel updateDeliverInfo(Long orderID, DeliveryInfoC deliveryInfo) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "deliveryInfo");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), deliveryInfo);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

    // Updating the payment status
    @SuppressWarnings("null")
    public OrderModel updatePaymentStaus(Long orderID, Boolean paymentDone) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "paymentDone");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), paymentDone);
            if (existingOrder.get().isPaymentDone() == true) {
                System.out.println("Order is confirmed");
                existingOrder.get().setManufactureDone("In Progress");
            }
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

    // Updating the manufacturing status
    @SuppressWarnings("null")
    public OrderModel updateManufactueDone(Long orderID, String maufactureDone) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "manufactureDone");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), maufactureDone);
            if (existingOrder.get().getManufactureDone().equals("Completed")) {
                System.out.println("Order is ready");
                existingOrder.get().setDeliveryStatus("Shipped");
            }
            return orderRepository.save(existingOrder.get());
        }
        return null;
    }

    // Updating the delivery status
    @SuppressWarnings("null")
    public OrderModel updateDeliveryStatus(Long orderID, String deliveryStatus) {
        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            Field field = ReflectionUtils.findField(OrderModel.class, "deliveryStatus");
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingOrder.get(), deliveryStatus);
            return orderRepository.save(existingOrder.get());
        }

        return null;
    }

}
