package com.example.travelink.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.dto.AccountDTO;
import com.example.travelink.model.Account;
import com.example.travelink.repository.CustomerRepository;

@Service
public class Customer_Service {
    
    @Autowired
    private CustomerRepository account_Repository;


    //get List Account
    public List<Account> getAllAccount(){
        return account_Repository.findAll();
    }

    //get List Account by role
    public List<Account> getCustomerByRole(int role){
        return account_Repository.findByRole(role);
    }

    //get List Account by email
    public Account getCustomerByEmail(String email){
        return account_Repository.findByEmail(email);
    }

    
    //register create new Account
    public Account registerNewCustomer(AccountDTO accountDTO){
        if(account_Repository.findByEmail(accountDTO.getEmail()) != null){
            throw new RuntimeException("Email is existed!");
        }
        //tạo đối tượng account mới
        Account account =  new Account();
        account.setName(accountDTO.getName());
        account.setPhoneNumber(accountDTO.getPhone());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setStatus(1);
        account.setRole(1);
        

    
        return account_Repository.save(account);
    }


}
