package com.example.springbootdemo.controller;
import com.example.springbootdemo.dto.RedisDto;
import com.example.springbootdemo.model.DessertModel;
import com.example.springbootdemo.model.OrderModel;
import com.example.springbootdemo.service.impl.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    //String
    @PostMapping("/getValue")
    public String queryUserAccountByKey(@RequestBody RedisDto redisDto) {
        return redisService.get(redisDto.getKey());
    }


    @PostMapping("/setValue")
    public void setValueKey(@RequestBody RedisDto redisDto) {
        redisService.set(redisDto.getKey(), redisDto.getValue());
    }

    @PostMapping("/lists/orders")
    public List<OrderModel> getHistoryList(@RequestBody RedisDto redisDto) {
        try {
            return redisService.getOrders(redisDto.getKey(), redisDto.getStart(), redisDto.getStop());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/lists/desserts")
    public List<DessertModel> getDessertList(@RequestBody RedisDto redisDto) {
        try {
            return redisService.getDessert(redisDto.getKey(), redisDto.getStart(), redisDto.getStop());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }



/*    @PostMapping("/queryTransactionHistory")
        public List<OrderTransactionHistoryModel> queryTransactionHistory(){
        redisService.hgetall();


    }*/

    }

    @PostMapping("/getDessertName")
    public  String getDessertName(@RequestBody RedisDto redisDto ){
        return redisService.hget(redisDto.getKey(),redisDto.getField());

    }
}



