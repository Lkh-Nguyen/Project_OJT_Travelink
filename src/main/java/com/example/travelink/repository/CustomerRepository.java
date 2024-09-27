package com.example.travelink.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.Account;

@Repository
public interface CustomerRepository extends JpaRepository<Account,Integer> {

    List<Account> findByRole(int role);

    Account findByEmail(String email);
    
}
