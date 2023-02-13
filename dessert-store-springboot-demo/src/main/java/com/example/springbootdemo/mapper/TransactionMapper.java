package com.example.springbootdemo.mapper;

import com.example.springbootdemo.dto.*;
import com.example.springbootdemo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TransactionMapper {

    CustomerModel queryCustomerById(CustomerDto customerDto);

    DessertModel queryDessertById (DessertDto dessertDto);

    Integer queryBalance(int walletId);

    List<DessertModel> queryDesserts();

    List<CustomerModel> queryCustomer();

    List<OrderModel> queryOrders();

    int queryDessertId(String dessertName);

    int queryDessertPrice(int dessertId);

    void insertOrder(OrderModel orderModel);

    void updateWallet(@Param("totalPrice") int totalPrice, @Param("customerId") int customerId);

    void updateOrder(OrderModel orderModel);

    void updateBalance(WalletUpdateBalanceDto walletUpdateBalanceDto);

    void deleteOrder(OrderDto orderDto);

    int addWallet(WalletModel walletModel);

    void addCustomer(CustomerModel customerModel);

    void updateTotalPrice(OrderModel orderModel);

    int  queryTotalPrice(OrderDto orderDto);

    void updateOrderDetails(OrderModel orderModel);

    void addDessert(DessertModel dessertModel);

    List<OrderTransactionHistoryModel> queryTransactionHistory();


}
