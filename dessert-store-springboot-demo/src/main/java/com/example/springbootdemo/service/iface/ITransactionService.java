package com.example.springbootdemo.service.iface;

import com.example.springbootdemo.dto.*;
import com.example.springbootdemo.model.*;
import com.example.springbootdemo.vo.OrderVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface ITransactionService {
    CustomerNewDto queryCustomerById(CustomerDto customerDto);

    DessertModel queryDessertById(DessertDto dessertDto);

    void addCustomer(CustomerDto customerDto);

     void addWallet (WalletModel walletModel);

    OrderVO processOrder(OrderDto orderDto) ;

    List<CustomerModel> queryCustomer();

    List<OrderModel> queryOrders() throws JsonProcessingException;

   void updateBalance(WalletUpdateBalanceDto walletUpdateBalanceDto);

   void deleteOrder(OrderDto orderDto);

   void updateOrderDetails(OrderDto orderDto);

   void addDessert(DessertDto dessertDto);

    List<DessertModel> queryDessert();




}
