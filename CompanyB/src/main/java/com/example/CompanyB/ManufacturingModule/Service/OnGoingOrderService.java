package com.example.CompanyB.ManufacturingModule.Service;

import com.example.CompanyB.ManufacturingModule.DataTransferObject.FinalOutput;
import com.example.CompanyB.ManufacturingModule.DataTransferObject.OnGoingOrder;
import com.example.CompanyB.ManufacturingModule.Model.WorkStationOne;
import com.example.CompanyB.ManufacturingModule.Model.WorkStationThree;
import com.example.CompanyB.ManufacturingModule.Model.WorkStationTwo;
import com.example.CompanyB.ManufacturingModule.Repository.FinalOutputRepository;
import com.example.CompanyB.ManufacturingModule.Repository.OnGoingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OnGoingOrderService {
    @Autowired
    private OnGoingOrderRepository onGoingOrderRepository;
    @Autowired
    private FinalOutputRepository finalOutputRepository;

    @Autowired
    public OnGoingOrderService(OnGoingOrderRepository onGoingOrderRepository, FinalOutputRepository finalOutputRepository ) {
        this.onGoingOrderRepository = onGoingOrderRepository;
        this.finalOutputRepository = finalOutputRepository;
    }

    public int GetCompletedNumber(String OrderId){
        OnGoingOrder Order = onGoingOrderRepository.findById(OrderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + OrderId));
        return  Order.getCompletedNum();

    }
    public boolean SetIsCompleted(String OrderId, boolean IsCompleted){
        OnGoingOrder Order = onGoingOrderRepository.findById(OrderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + OrderId));
        Order.setCompleted(IsCompleted);
        onGoingOrderRepository.save(Order);
        return IsCompleted;
    }

    public OnGoingOrder GetOrderToWorkStation(String orderId){

        return onGoingOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

    }

    public OnGoingOrder WorkStationOneFetch (String orderId, int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationOne.fetch(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
        }
        else{
            //allocated to handle invalid amount
        }
        return onGoingOrder;
    }

    public OnGoingOrder WorkStationOnePass (String orderId,int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationOne.pass(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
        }
        else{
            //allocated to handle invalid amount
        }

      return onGoingOrder;
    }
    public OnGoingOrder WorkStationTwoFetch (String orderId, int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationTwo.fetch(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
        }
        else{
            //allocated to handle invalid amount
        }
        return onGoingOrder;
    }
    public OnGoingOrder WorkStationTwoPass (String orderId,int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationTwo.pass(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
        }
        else{
            //allocated to handle invalid amount
        }

        return onGoingOrder;
    }
    public OnGoingOrder WorkStationThreeFetch (String orderId, int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationThree.fetch(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
        }
        else{
            //allocated to handle invalid amount
        }
        return onGoingOrder;
    }
    public OnGoingOrder WorkStationThreePass (String orderId,int amount){
        OnGoingOrder onGoingOrder = onGoingOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        int success = WorkStationThree.pass(onGoingOrder,amount);
        if(success ==0){
            onGoingOrderRepository.save(onGoingOrder);
            // say that success
        }
       else if (success ==1) {
          FinalOutput finalOutput = new FinalOutput(onGoingOrder.getId(),onGoingOrder.getCompletedNum(),0,false, LocalDate.now());
          onGoingOrderRepository.save(onGoingOrder);
          try{finalOutputRepository.insert(finalOutput);}
          catch (RuntimeException e){
              //allocated to handle entering already completed order.
          }
      }
        else{
            //allocated to handle invalid amount
        }

        return onGoingOrder;
    }
}
