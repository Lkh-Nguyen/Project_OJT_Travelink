package com.example.travelink.controller;

import com.example.travelink.model.Account;
import com.example.travelink.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordController {

    @Autowired
    private CustomerService customerService;

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
        // System.out.println("Logged-in user: " + customer.getEmail()); // Log để kiểm tra
        //Kiểm tra mật khẩu cũ có đúng không
        if(!password.equalsIgnoreCase(customer.getPassword())){
            return "redirect:/CustomerChangePassword";
        }

        // Kiểm tra mật khẩu mới có khớp nhau không
        if (!new_password.equals(new_again_password)) {
            return "redirect:/CustomerChangePassword";
        }

        if (customer != null){
            customer.setPassword(new_again_password);
            customerService.updateCustomerInformation(customer);
            httpSession.setAttribute("customer", customer);
        }
        
        // Thông báo thành công
        return "redirect:/CustomerChangePassword";
    }
}
