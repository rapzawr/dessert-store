package com.example.springbootdemo.service.impl;
import com.example.springbootdemo.dto.*;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.TransactionMapper;
import com.example.springbootdemo.model.*;
import com.example.springbootdemo.service.iface.ITransactionService;
import com.example.springbootdemo.vo.OrderVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Service
public class TransactionServiceImpl implements ITransactionService {

    @Resource
    TransactionMapper transactionMapper;

    @Resource
    RedisService redisService;

    String dessertKey= "DessertKey";
    String orderKey  = "Orders";


    @PostConstruct
    private void loadDesserts() {
    List <DessertModel> listOfDesserts = transactionMapper.queryDesserts();

    for(DessertModel dessert: listOfDesserts){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String json=objectMapper.writeValueAsString(dessert);
            redisService.set(dessertKey,json);
            System.out.println(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    }
   /* @Override
    public List<DessertModel> queryDesserts(){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String , String >dessertModels= redisService.getDesserts(dessertKey);
        List<DessertModel>dessert = new ArrayList<>();

        for(Map.Entry<String,String> set : dessertModels.entrySet()){
            try{
               DessertModel dessertModel =objectMapper.readValue(set.getValue(),DessertModel.class);
            }
        }
    }*/

    @Override
    public CustomerNewDto queryCustomerById(CustomerDto customerDto) {
        CustomerModel customerModel= transactionMapper.queryCustomerById(customerDto);
        redisService.set(String.valueOf(customerModel.getCustomerId()),customerModel.getCustomerName());
        CustomerNewDto customerNewDto = new CustomerNewDto();
        BeanUtils.copyProperties(customerModel,customerNewDto);
        return customerNewDto;
    }

    @Override
    public DessertModel queryDessertById(DessertDto dessertDto){
        return transactionMapper.queryDessertById(dessertDto);
    }

    
    @Override
    public List<CustomerModel> queryCustomer(){
       List<CustomerModel> customers= transactionMapper.queryCustomer();

       for(CustomerModel customerr : customers){
           redisService.set(String.valueOf(customerr.getCustomerId()), customerr.getCustomerName());
       }
       return customers;
    }

    @Override
    public List<OrderModel> queryOrders() throws JsonProcessingException {
        List<OrderModel> orders= transactionMapper.queryOrders();

        for(OrderModel order: orders){
            redisService.setList(orderKey, order);
        }

        return orders;
    }

    @Override
    public void  addWallet(WalletModel walletModel){
         transactionMapper.addWallet(walletModel);
    }

    @Override
    @Transactional
    public void addCustomer (CustomerDto customerDto){
        WalletModel walletModel = new WalletModel();
        walletModel.setBalance(0);
        addWallet(walletModel);
        CustomerModel customerModel= new CustomerModel();
        customerModel.setWalletId(walletModel.getWalletId());
        customerModel.setCustomerName(customerDto.getCustomerName());
        customerModel.setAddress(customerDto.getAddress());
        customerModel.setContactNo(customerDto.getContactNo());
        transactionMapper.addCustomer(customerModel);
    }

    @Override
    public void updateOrderDetails(OrderDto orderDto){

      int dessertId = transactionMapper.queryDessertId(orderDto.getDessertName());
      int price = transactionMapper.queryDessertPrice(dessertId);

      OrderModel orderModel = new OrderModel();
      orderModel.setOrderId(orderDto.getOrderId());
      orderModel.setDessertId(dessertId);
      orderModel.setQuantity(orderDto.getQuantity());
      orderModel.setTotalPrice(orderDto.getQuantity()* price);
      transactionMapper.updateOrderDetails(orderModel);



    }

    @Override
    public void updateBalance(WalletUpdateBalanceDto walletUpdateBalanceDto){
        transactionMapper.updateBalance(walletUpdateBalanceDto);
    }

    @Override
    public List<DessertModel> queryDessert() {


        List<DessertModel> dessertModels=null;
        try {
            dessertModels=redisService.lrangeList(dessertKey);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dessertModels;



    }

    @Override
    public void deleteOrder(OrderDto orderDto){
        transactionMapper.deleteOrder(orderDto);
    }


    @Transactional
    @Override
    public OrderVO processOrder(OrderDto orderDto)  {
        int dessertId = transactionMapper.queryDessertId(orderDto.getDessertName());
        int price = transactionMapper.queryDessertPrice(dessertId);

        OrderModel orderModel = new OrderModel();
        orderModel.setCustomerId(orderDto.getCustomerId());
        orderModel.setDessertId(dessertId);
        orderModel.setQuantity(orderDto.getQuantity());
        orderModel.setTotalPrice(orderDto.getQuantity() * price);
        transactionMapper.insertOrder(orderModel);
        OrderVO orderVO = new OrderVO();
        orderVO.setDessertName(orderDto.getDessertName());
        orderVO.setQuantity(orderDto.getQuantity());
        orderVO.setTotalPrice(orderModel.getTotalPrice());

        transactionMapper.updateWallet(orderModel.getTotalPrice(), orderDto.getCustomerId());

        CustomerDto customerDto=new CustomerDto();
        customerDto.setCustomerId(orderDto.getCustomerId());
        CustomerModel customerModel = transactionMapper.queryCustomerById(customerDto);
        Integer balance = transactionMapper.queryBalance(customerModel.getWalletId());

        if (balance <= 0){
            throw new ServiceException();

        }

        transactionMapper.updateOrder(orderModel);
        orderVO.setIsDelivered(1);
        return orderVO;
}

    @Override
    public void addDessert(DessertDto dessertDto) {

        DessertModel dessertModel = new DessertModel();

        dessertModel.setDessertName(dessertDto.getDessertName());
        dessertModel.setPrice(dessertDto.getPrice());
        dessertModel.setIsAvailable(dessertDto.getIsAvailable());
        transactionMapper.addDessert(dessertModel);

    }


}

