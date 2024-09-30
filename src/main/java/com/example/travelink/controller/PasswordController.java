package com.example.travelink.controller;

import com.example.travelink.model.Account;
import com.example.travelink.service.CustomerService;
// import com.example.travelink.service.PasswordService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordController {
   
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customer_Service;


    @GetMapping("/CustomerChangePassword")
    public String changePassword(Model model, HttpSession session) {
        // Lấy người dùng từ session thay vì từ login
        Account account = (Account) session.getAttribute("customer");

        if (account == null) {
            return "redirect:/CustomerLoginRegister";
        }

        model.addAttribute("customer", account);
        return "Customer_Change_Password";
    }

    @PostMapping("/CustomerChangePassword")
    public String changePassword(@RequestParam("password") String password,
                                @RequestParam("new_password") String new_password,
                                @RequestParam("new_again_password") String new_again_password,
                                HttpSession httpSession) throws Exception {
        Account customer = (Account) httpSession.getAttribute("customer");

        // Kiểm tra nếu tài khoản đang đăng nhập không tồn tại
        if (customer == null) {
            return "redirect:/CustomerLoginRegister";
        }

        System.out.println("Logged-in user: " + customer.getEmail()); // Log để kiểm tra


        //Kiểm tra mật khẩu cũ có đúng không
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            return "redirect:/CustomerChangePassword";
        }

        // Kiểm tra mật khẩu mới có khớp nhau không
        if (!new_password.equals(new_again_password)) {
            return "redirect:/CustomerChangePassword";
        } else {
            // Mã hóa mật khẩu mới trước khi lưu
            String encodedNewPassword = passwordEncoder.encode(new_password);
            if (customer != null){
            customer.setPassword(encodedNewPassword);
            customer_Service.updateCustomerInformation(customer);
            httpSession.setAttribute("customer", customer);
        }
        }

        
        
        // Thông báo thành công
        return "redirect:/CustomerChangePassword";
    }
     public static void main(String[] args) {
        String hashedPassword = "$2a$10$ux/KM81k.EHDcqzO4hxqK.FoxS6qR1zppilBVngybc5xnrjp4/M8O";
        String rawPassword = "12345678"; // Replace with the actual password you want to check

        // Validate the password
        if (BCrypt.checkpw(rawPassword, hashedPassword)) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Invalid password.");
        }
    }
}
