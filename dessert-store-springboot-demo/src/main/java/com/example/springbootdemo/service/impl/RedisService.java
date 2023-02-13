package com.example.springbootdemo.service.impl;
import com.example.springbootdemo.dto.RedisDto;
import com.example.springbootdemo.model.DessertModel;
import com.example.springbootdemo.model.OrderModel;
import com.example.springbootdemo.model.OrderTransactionHistoryModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class RedisService {

    @Value("${com.example.springbootdemo.service.redis.ttl}")
    private Integer ttl;

    @Value("${com.example.springbootdemo.service.redis.url}")
    private String url;

    private RedisClient redisClient;

    private StatefulRedisConnection<String, String> statefulRedisConnection;

    //
    public String get(String key) {
        return statefulRedisConnection.sync().get(key);
    }

    public void set(String key, String value) {

        statefulRedisConnection.sync().lpush(key,value);
    }

    public List<DessertModel> lrangeList(String key) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        List<String> list=statefulRedisConnection.sync().lrange(key, 0, -1);
        List<DessertModel> dessertModels=new ArrayList<>();
        for (String i : list) {
//            try {
//                dessertModels.add(objectMapper.readValue(i, DessertModel.class));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                return null;
//            }
        }


        return dessertModels;
    }

    //object
    public void setList(String key, OrderModel orderModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        statefulRedisConnection.sync().lpush(key, objectMapper.writeValueAsString(orderModel));
    }

    public List<OrderModel> getOrders(String key, long start, long stop) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderModel> orderModelList = new ArrayList<>();
            for(String jsonObj : statefulRedisConnection.sync().lrange(key, start, stop)) {
            System.out.println(jsonObj);
//            orderModelList.add(objectMapper.readValue(jsonObj, OrderModel.class));
        }

       return orderModelList;
    }

    public List<DessertModel> getDessert(String key, long start, long stop) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DessertModel> dessertModelList = new ArrayList<>();
        for(String jsonObj : statefulRedisConnection.sync().lrange(key, start, stop)) {
//            System.out.println(jsonObj);
//            dessertModelList.add(objectMapper.readValue(jsonObj, DessertModel.class));
        }

        return dessertModelList;
    }

    public void hset(String key,String field,String value){
//        HashMap<String,String> hashMap = new HashMap<>();
//        hashMap.put(field,value);
        statefulRedisConnection.sync().hset(key, field, value);
    }
    public String hget(String key,String field){
        return statefulRedisConnection.sync().hget(key,field);
    }
    public String getDessertName(String key,String field){

        return RedisService.this.hget(key,field);

    }


    public List<OrderTransactionHistoryModel> hgetall(String key){

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> lists = statefulRedisConnection.sync().hgetall(key);

        List<OrderTransactionHistoryModel> history = new ArrayList<>();

        for (Map.Entry<String, String> entry : lists.entrySet()) {
            try {
                history.add(objectMapper.readValue(entry.getValue(), OrderTransactionHistoryModel.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return history;
    }


    @PostConstruct
    private void init(){
        redisClient = RedisClient.create(url);
        statefulRedisConnection = redisClient.connect();
    }

    @PreDestroy
    private void destroy(){
        if(statefulRedisConnection != null){
            statefulRedisConnection.close();
        }

        if(redisClient != null){
            redisClient.shutdown();
        }

    }
    public void listPush(String key, String value){
        statefulRedisConnection.sync().lpush(key,value);
    }
    public List listRange(String key, long start, long stop){
        return statefulRedisConnection.sync().lrange(key, start, stop);
    }

    public Map<String,String> getDesserts(String key){
        return statefulRedisConnection.sync().hgetall(key);
    }


}

