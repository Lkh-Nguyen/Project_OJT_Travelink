package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelink.dto.AccountDTO;
import com.example.travelink.model.Account;
import com.example.travelink.service.Customer_Service;

@Controller
public class Customer_Controller {

    @Autowired
    private Customer_Service customer_Service;


    @GetMapping("/CustomerHome")
    public String customerHome() {
        return "Customer_Home"; // Trả về trang home sau khi login thành công
    }

    @GetMapping("/CustomerLoginRegister")
    public String customerLoginRegister() {
        return "Customer_Login_Register"; // Trả về trang home sau khi login thành công
    }

    @GetMapping("/CustomerForgetPassword")
    public String customerForgetPassword() {
        return "Customer_Forget_Password"; // Trả về trang home sau khi login thành công
    }


    @GetMapping("/CustomerVerifyCode")
    public String customerVerifyCode() {
        return "Customer_Verify_Code"; // Trả về trang home sau khi login thành công
    }

    @GetMapping("/CustomerViewInformation")
    public String customerViewInformation() {
        return "Customer_View_Information"; // Trả về trang home sau khi login thành công
    }

    @GetMapping("/CustomerChangePassword")
    public String customerChangePassword() {
        return "Customer_Change_Password"; // Trả về trang home sau khi login thành công
    }


    



   

    // Xử lý đăng nhập
    @PostMapping("/Login")
    public String getCustomerByEmail(@RequestParam("email") String email, 
                                     @RequestParam("password") String password, 
                                     RedirectAttributes redirectAttributes) {
        Account account = customer_Service.getCustomerByEmail(email);
        
        if (account != null) {
            if (account.getPassword().equals(password)) {
                redirectAttributes.addFlashAttribute("message", "User tồn tại. Login thành công.");
                return "redirect:/Home";  // Chuyển hướng tới trang home
            } else {
                redirectAttributes.addFlashAttribute("message", "Mật khẩu sai rồi. Nhập lại.");
                return "redirect:/Login";  // Quay lại trang login nếu sai mật khẩu
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Email chưa được đăng ký.");
            return "redirect:/Login";  // Quay lại trang login nếu email không tồn tại
        }
    }

    @PostMapping("/Register")
    public String registerAccount(
        @RequestParam("name") String name,
        @RequestParam("phone") String phone,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        Model model
    ) {
        try {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setName(name);
            accountDTO.setPhone(phone);
            accountDTO.setEmail(email);
            accountDTO.setPassword(password); // Sẽ được mã hóa trong service

            customer_Service.registerNewCustomer(accountDTO);
            model.addAttribute("message","Create thành công");
            return "Customer_LoginRegister";
        } catch (Exception e) {
            model.addAttribute("message","Create thất bại");
            return "Customer_LoginRegister";
        }
    }
}
