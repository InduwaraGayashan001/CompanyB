package com.example.CompanyB.CustomerOrderMnaagementModule.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderInfoC {

    private int layers;
    private double thickness;
    private List<Double> dimensions;
    private int quantity;

    
}
