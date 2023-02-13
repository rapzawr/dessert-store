package com.example.springbootdemo.service.iface;

import com.example.springbootdemo.model.OrderTransactionHistoryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ITransactionHistoryService
{

    List<OrderTransactionHistoryModel> queryOrderTransactionHistory();
    String getDessertName(String key,String field);

}
