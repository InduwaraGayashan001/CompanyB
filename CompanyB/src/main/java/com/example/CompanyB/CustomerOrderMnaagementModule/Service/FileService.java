package com.example.CompanyB.CustomerOrderMnaagementModule.Service;

import com.example.CompanyB.CustomerOrderMnaagementModule.Model.DatabaseSequence;
import com.example.CompanyB.CustomerOrderMnaagementModule.Model.OrderModel;
import com.example.CompanyB.CustomerOrderMnaagementModule.Repository.OrderRepository;
import com.example.CompanyB.CustomerOrderMnaagementModule.Util.ImageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoOperations mongoOperations;


    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("id_").is(seqName)),
                new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }


    public String uploadImage(MultipartFile file, String customerId) {
        try {
            
            OrderModel newOrder = new OrderModel(customerId, ImageUtils.compressImage(file.getBytes()),generateSequence(OrderModel.SEQUENCE_NAME));
            orderRepository.save(newOrder);
            return "File uploaded successfully: " + file.getOriginalFilename();
            
        } catch (IOException e) {
            // Handle file read/compression errors
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        } catch (Exception e) {
            // Handle other unexpected errors
            e.printStackTrace();
            return "Internal server error: " + e.getMessage();
        }
    }

    public byte[] downloadImage(Long orderID) {

        Optional<OrderModel> existingOrder = orderRepository.findOrderByOrderID(orderID);
        if (existingOrder.isPresent()) {
            OrderModel order = existingOrder.get();
            byte[] dbImageData = order.getPcbFile(); // Assuming getPcbFile() returns byte[]
            return ImageUtils.decompressImage(dbImageData); // Assuming ImageUtils.decompressImage() is properly
                                                            // implemented
        }
        return null;
    }
}