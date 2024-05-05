package com.example.CompanyB.CustomerOrderMnaagementModule.Model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    @Id
    private ObjectId id;

    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    private Long orderID;
    private String customerID;

    private byte[] pcbFile;

    private OrderInfoC orderInfo;

   
    private boolean simulationStatus;
    private boolean partsAvailable;

    private DeliveryInfoC deliveryInfo;

    private double payment;
    private boolean paymentDone;

    private String manufactureDone;
    private String deliveryStatus;

    @DocumentReference
    private List<Feedback> feedback;

    public OrderModel(String customerID, byte[] pcbFile, long orderID) {
        this.customerID = customerID;
        this.pcbFile = pcbFile;
        this.orderID = orderID;
    }

    

}
