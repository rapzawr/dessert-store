package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.exception.NoDataFoundException;
import com.example.springbootdemo.mapper.TransactionMapper;
import com.example.springbootdemo.model.OrderTransactionHistoryModel;
import com.example.springbootdemo.service.iface.ITransactionHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class TransactionHistoryImpl implements ITransactionHistoryService {

    @Resource
    private TransactionMapper transactionMapper;

    @Resource
    RedisService redisService;

    String key = "key";

    @PostConstruct
    public void saveOrderTransactionHistory(){

        List<OrderTransactionHistoryModel> transactionHistoryList = transactionMapper.queryTransactionHistory();

        if(isEmpty(transactionHistoryList)){
            try {
                throw new NoDataFoundException();
            } catch (NoDataFoundException e) {
                e.printStackTrace();
            }
        }

        for(OrderTransactionHistoryModel history : transactionHistoryList){

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                redisService.hset(key,Integer.toString(history.getOrderId()), objectMapper.writeValueAsString(history));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getDessertName (String key,String field){

        return  redisService.getDessertName(key, field);
    }

    public List<OrderTransactionHistoryModel> queryOrderTransactionHistory() {
        return redisService.hgetall(key);
    }

    private boolean isEmpty(List<OrderTransactionHistoryModel> list){
        if(list.size() == 0){
            return true;
        }
        return false;
    }
}
