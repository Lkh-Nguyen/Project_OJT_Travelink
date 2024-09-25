package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Đúng import từ Spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.travelink.model.Account;
import com.example.travelink.service.Customer_Service;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateInformationCustomerController {
    @Autowired
    private Customer_Service customer_Service;

    // @GetMapping("/mockLogin")
    // public String mockLogin(HttpSession session) {

    // Account account = customer_Service.getAccountById(2);
    // session.setAttribute("loggedInUser", account);
    // return "redirect:/ViewInformationCustomer";
    // }

    @GetMapping("/ViewInformationCustomer")
    public String viewInformationCustomer(Model model, HttpSession session) {
        // Lấy người dùng từ session thay vì từ login
        Account account = (Account) session.getAttribute("loggedInUser");

        if (account == null) {
            return "redirect:/CustomerLoginRegister";
        }

        model.addAttribute("account", account);
        return "Customer_View_Information";
    }

    @PostMapping("/UpdateInformationCustomer")
    public String updateInformationCustomer(@ModelAttribute("account") Account account, HttpSession session,
            Model model) {
        // Cập nhật thông tin người dùng
        if (customer_Service.isEmailAlreadyInUse(account.getEmail())) {
            model.addAttribute("error", "Email đã được sử dụng. Vui lòng nhập email khác.");
            return "redirect:/ViewInformationCustomer"; // Trả về form để nhập lại email
        }
        customer_Service.UpdateInformationCustomer(account);
        session.setAttribute("loggedInUser", account);
        return "redirect:/ViewInformationCustomer";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/ViewInformationCustomer";
    }

}
