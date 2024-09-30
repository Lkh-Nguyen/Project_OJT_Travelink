package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Đúng import từ Spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelink.model.Account;
import com.example.travelink.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateInformationCustomerController {
    @Autowired
    private CustomerService customer_Service;

    @GetMapping("/CustomerViewInformation")
    public String viewInformationCustomer(Model model, HttpSession session) {
        // Lấy người dùng từ session thay vì từ login
        Account account = (Account) session.getAttribute("customer");

        if (account == null) {
            return "redirect:/CustomerLoginRegister";
        }

        model.addAttribute("customer", account);
        return "Customer_View_Information";
    }

   @PostMapping("/UpdateInformationCustomer")
    public String updateInformationCustomer(
            @RequestParam(value = "name", required=false) String name,
            @RequestParam(value = "gender",required=false) String gender,
            @RequestParam(value = "dateOfBirth",required=false) String dateOfBirth,
            @RequestParam(value = "cmnd",required=false) String cmnd,
            @RequestParam(value = "phoneNumber",required=false) String phoneNumber,
            @RequestParam(value = "email",required=false) String email,
            @RequestParam(value = "address",required=false) String address,
            HttpSession session) {

        Account customer = (Account) session.getAttribute("customer");

        if (customer != null) {
            customer.setName(name);
            customer.setGender(gender);

            if(dateOfBirth != "") {
                customer.setDateOfBirth(java.sql.Date.valueOf(dateOfBirth));
            }

            customer.setCmnd(cmnd);
            customer.setPhoneNumber(phoneNumber);
            customer.setEmail(email);
            customer.setAddress(address);

            customer_Service.updateCustomerInformation(customer);
            session.setAttribute("customer", customer);
        }

        return "redirect:/CustomerViewInformation";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/CustomerViewInformation";
    }

}
