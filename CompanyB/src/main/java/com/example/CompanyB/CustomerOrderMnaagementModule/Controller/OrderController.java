package com.example.CompanyB.CustomerOrderMnaagementModule.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.CompanyB.CustomerOrderMnaagementModule.Model.DeliveryInfoC;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.Feedback;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.OrderInfoC;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.OrderModel;
import com.example.CompanyB.CustomerOrderMnaagementModule.Service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/customer/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderModel>> getallOrders() {
        return new ResponseEntity<List<OrderModel>>(orderService.allOrders(), HttpStatus.OK);
    }
    

    // Getting order by order id
    @GetMapping("/{orderID}")
    public ResponseEntity<Optional<OrderModel>> getSingleOrder(@PathVariable Long orderID) {
        return new ResponseEntity<Optional<OrderModel>>(orderService.singleOrder(orderID), HttpStatus.OK);
    }

    // Getting the all orders of a customer
    @GetMapping("/{customerID}/all")
    public ResponseEntity<List<OrderModel>> getallCustomerOrders(@PathVariable String customerID) {
        return new ResponseEntity<List<OrderModel>>(orderService.allCustomerOrders(customerID), HttpStatus.OK);
    }

    // Getting the delievery info
    @GetMapping("/{orderID}/orderInfo")
    public ResponseEntity<OrderInfoC> getOrderInfo(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            OrderInfoC orderInfo = order.getOrderInfo();
            return new ResponseEntity<>(orderInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Getting the simulation status
    @GetMapping("/{orderID}/simulation")
    public ResponseEntity<Boolean> getSimulationStatus(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            boolean simulationStatus = order.isSimulationStatus();
            return new ResponseEntity<>(simulationStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the parts availabilyty
    @GetMapping("/{orderID}/parts")
    public ResponseEntity<Boolean> getPartsAvailability(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            boolean simulationStatus = order.isPartsAvailable();
            return new ResponseEntity<>(simulationStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the price of the oder
    @GetMapping("/{orderID}/price")
    public ResponseEntity<Double> getPrice(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            double payment = order.getPayment();
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the payment status
    @GetMapping("/{orderID}/payment")
    public ResponseEntity<Boolean> getPaymentStatus(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            boolean paymentDone = order.isPaymentDone();
            return new ResponseEntity<>(paymentDone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the manufacturing status
    @GetMapping("/{orderID}/manufacturing")
    public ResponseEntity<String> getManufactureStatus(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            String maufactureDone = order.getManufactureDone();
            return new ResponseEntity<>(maufactureDone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the delievery info
    @GetMapping("/{orderID}/deliveryInfo")
    public ResponseEntity<DeliveryInfoC> getDeliveryInfo(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            DeliveryInfoC deliveryInfo = order.getDeliveryInfo();
            return new ResponseEntity<>(deliveryInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the delievery status
    @GetMapping("/{orderID}/delivery")
    public ResponseEntity<String> getDeleiveryStatus(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            String deliveryStatus = order.getDeliveryStatus();
            return new ResponseEntity<>(deliveryStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Getting the feedback
    @GetMapping("/{orderID}/feedback")
    public ResponseEntity<List<Feedback>> getFeedback(@PathVariable Long orderID) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel order = orderOptional.get();
            List<Feedback> feedback = order.getFeedback();
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    // Updating the order information
    @PatchMapping("/{orderID}/orderInfo")
    public ResponseEntity<OrderModel> updateDeliveryInfo(@PathVariable Long orderID,@RequestBody OrderInfoC orderInfo) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updateOrderInfo(orderID, orderInfo);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the simulation status
    @PatchMapping("/{orderID}/simulation")
    public ResponseEntity<OrderModel> updateSimulationStatus(@PathVariable Long orderID,
            @RequestBody boolean simulationStatus) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updateSimulationStatus(orderID, simulationStatus);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the parts availability
    @PatchMapping("/{orderID}/parts")
    public ResponseEntity<OrderModel> updatePartsAvailable(@PathVariable Long orderID,
            @RequestBody boolean partsAvailable) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updatePartsAvailable(orderID, partsAvailable);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the total price of the order
    @PatchMapping("/{orderID}/price")
    public ResponseEntity<OrderModel> updatePayment(@PathVariable Long orderID, @RequestBody double payment) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updatePayment(orderID, payment);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the delivery info
    @PatchMapping("/{orderID}/deliveryInfo")
    public ResponseEntity<OrderModel> updateDeliveryInfo(@PathVariable Long orderID,@RequestBody DeliveryInfoC deliveryInfo) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updateDeliverInfo(orderID, deliveryInfo);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the payment status
    @PatchMapping("/{orderID}/payment")
    public ResponseEntity<OrderModel> updatePaymentStatus(@PathVariable Long orderID,
            @RequestBody boolean paymentDone) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updatePaymentStaus(orderID, paymentDone);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the manufacturing status
    @PatchMapping("/{orderID}/manufacturing")
    public ResponseEntity<OrderModel> updateManufactureDone(@PathVariable Long orderID,
            @RequestBody String manufactureDone) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updateManufactueDone(orderID, manufactureDone);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updating the delivery status
    @PatchMapping("/{orderID}/delivery")
    public ResponseEntity<OrderModel> updateDeliveryStatus(@PathVariable Long orderID,
            @RequestBody String deliveryStatus) {
        Optional<OrderModel> orderOptional = orderService.singleOrder(orderID);
        if (orderOptional.isPresent()) {
            OrderModel orderModel = orderService.updateDeliveryStatus(orderID, deliveryStatus);
            return new ResponseEntity<>(orderModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
