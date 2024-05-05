package com.example.CompanyB.CustomerOrderMnaagementModule.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfoC {
    private String deliveryAddress;
    private String country;
    private long contactNum;
    private String contactPersonName;
    
}
