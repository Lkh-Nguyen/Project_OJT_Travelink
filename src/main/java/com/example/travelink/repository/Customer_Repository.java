package com.example.travelink.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.Account;

@Repository
public interface Customer_Repository extends JpaRepository<Account,Integer> {

    //get List Account by role
    List<Account> findByRole(int role);
    Account findByEmail(String email);

    
}
