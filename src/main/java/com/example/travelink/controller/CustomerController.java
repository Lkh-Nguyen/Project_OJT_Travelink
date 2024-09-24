package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelink.dto.AccountDTO;
import com.example.travelink.model.Account;
import com.example.travelink.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customer_Service;


    @GetMapping("/CustomerHome")
    public String customerHome(HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            return "redirect:/CustomerLoginRegister"; // Redirect if not logged in
        }
        model.addAttribute("user", user);
        return "Customer_Home"; // Return the home view
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

    @GetMapping("/OAuthCustomerHome")
    public String getUserLoginByGmail(OAuth2AuthenticationToken authentication, Model model) {
        
        OAuth2User oauth2User = authentication.getPrincipal();

        Account user = customer_Service.registerByOAuth(oauth2User);

        // Add user attributes to the model
        model.addAttribute("user", user);

        // Return the view name
        return "Customer_Home"; // This should correspond to home.html
    }


}
