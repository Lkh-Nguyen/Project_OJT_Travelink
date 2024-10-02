package com.example.travelink.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.travelink.dto.AccountDTO;
import com.example.travelink.model.Account;
import com.example.travelink.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    
    @Autowired
    private final CustomerRepository customerRepository;

    //get List Account
    public List<Account> getAllAccount(){
        return customerRepository.findAll();
    }

    //get List Account by role
    public List<Account> getCustomerByRole(int role){
        return customerRepository.findByRole(role);
    }

    //get List Account by email
    public Account getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    //get customer by id
    public Account getCustomerByID(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    //update information
    public void updateCustomerInformation(Account account) {
        customerRepository.save(account);
    }
    
    public boolean isEmailAlreadyInUse(String email) {
        return customerRepository.findByEmail(email) != null;
    }

    //register create new Account
    public Account registerNewCustomer(AccountDTO accountDTO){
        if(customerRepository.findByEmail(accountDTO.getEmail()) != null){
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
        
        return customerRepository.save(account);
    }

    //register create google Account
    public Account registerByOAuth(OAuth2User oAuth2User) {
        //Get profile user
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");

        //Check exist
        Account existingAccount = customerRepository.findByEmail(email);
        
        //Return if already register
        if (existingAccount != null) {
            return existingAccount;
        }
        
        // Nếu chưa tồn tại, tạo mới account
        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setName(name);
        newAccount.setPassword(null);
        newAccount.setCmnd(null);
        newAccount.setGender("M");
        newAccount.setDateOfBirth(null);
        newAccount.setAvatarUrl(picture);
        newAccount.setPhoneNumber(null);
        newAccount.setAddress(null);
        newAccount.setRole(1); // 1: User, 0: Admin
        newAccount.setStatus(1); // 1: Active

        
        return customerRepository.save(newAccount);
    }

    //Update Avatar Of Customer
    public void updateAvatarUrl(String email, String imageUrl) {
        Account account = customerRepository.findByEmail(email);
        if (account != null) {
            account.setAvatarUrl(imageUrl);; // Cập nhật avatarUrl
            customerRepository.save(account); // Lưu thay đổi
        }
    }

    //Save account ??
    public void saveAccount(Account account) {
        customerRepository.save(account);
    }
    
    
}
