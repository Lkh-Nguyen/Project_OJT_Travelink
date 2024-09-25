package com.example.travelink.controller;

import com.example.travelink.model.Account;
import com.example.travelink.service.PasswordService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/mockLogin")
    public String mockLogin(HttpSession session) {

    Account account = passwordService.getAccountById(5);
    session.setAttribute("loggedInUser", account);
    return "redirect:/CustomerChangePassword";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model, HttpSession session) {
        // Lấy người dùng từ session thay vì từ login
        Account account = (Account) session.getAttribute("loggedInUser");

        if (account == null) {
            return "redirect:/mockLogin";
        }

        model.addAttribute("account", account);
        return "Customer_Change_Password";
    }

    // Change password endpoint
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam("accountId") int accountId,  // Tham số accountId vẫn cần phải truyền vào
            @RequestParam("password") String currentPassword,  // Tên trường đã thay đổi
            @RequestParam("new_password") String newPassword,  // Tên trường đã thay đổi
            @RequestParam("new_again_password") String confirmNewPassword) {  // Tên trường đã thay đổi
    
        // Kiểm tra xem mật khẩu mới và mật khẩu xác nhận có giống nhau không
        if (!newPassword.equals(confirmNewPassword)) {
            return ResponseEntity.badRequest().body("New password and confirmation password do not match.");
        }
    
        // Gọi service để thay đổi mật khẩu
        boolean result = passwordService.changePassword(accountId, currentPassword, newPassword);
    
        // Kiểm tra kết quả
        if (!result) {
            return ResponseEntity.badRequest().body("Current password is incorrect or account not found.");
        }
    
        return ResponseEntity.ok("Password changed successfully.");
    }
    
}
