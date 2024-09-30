package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.example.travelink.model.VerificationToken;
import com.example.travelink.service.CustomerService;
import com.example.travelink.service.MailService;
import com.example.travelink.service.VerificationTokenService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MailService mailService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/CustomerHome")
    public String customerHome(HttpSession session, Model model) {
        Account customer = (Account) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/CustomerLoginRegister"; // Redirect if not logged in
        }
        model.addAttribute("customer", customer);
        return "Customer_Home"; // Return the home view
    }

    @GetMapping("/CustomerLoginRegister")
    public String customerLoginRegister() {
        return "Customer_Login_Register"; // Trả về trang login/register
    }

    @GetMapping("/CustomerForgetPassword")
    public String customerForgetPassword() {
        return "Customer_Forget_Password"; // Trả về trang quên mật khẩu
    }

    @GetMapping("/CustomerVerifyCode")
    public String customerVerifyCode() {
        return "Customer_Verify_Code"; // Trả về trang xác minh mã
    }

    @GetMapping("/OAuthCustomerHome")
    public String getUserLoginByGmail(OAuth2AuthenticationToken authentication, Model model, HttpSession session) {

        OAuth2User oauth2User = authentication.getPrincipal();

        Account account = customerService.registerByOAuth(oauth2User);

        session.setAttribute("customer", account);

        model.addAttribute("customer", account);
        // Return the view name
        return "Customer_Home"; // This should correspond to home.html
    }

    @PostMapping("/login")
    public String getCustomerByEmail(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        Account account = customerService.getCustomerByEmail(email);

        if (account != null) {
            // Sử dụng passwordEncoder để so sánh mật khẩu
            if (passwordEncoder.matches(password, account.getPassword())) {
                // Lưu thông tin khách hàng vào session
                session.setAttribute("customer", account);
                redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công.");
                return "redirect:/CustomerHome"; // Chuyển hướng tới trang home
            } else {
                redirectAttributes.addFlashAttribute("message", "Mật khẩu sai. Vui lòng thử lại.");
                return "redirect:/CustomerLoginRegister"; // Quay lại trang đăng nhập nếu sai mật khẩu
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Email chưa được đăng ký.");
            return "redirect:/CustomerLoginRegister"; // Quay lại trang đăng nhập nếu email không tồn tại
        }
    }

    @PostMapping("/register")
    public String registerAccount(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        try {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setName(name);
            accountDTO.setPhone(phone);
            accountDTO.setEmail(email);
            accountDTO.setPassword(password);

            Account account = customerService.registerNewCustomer(accountDTO);

            // Generate a verification token
            VerificationToken token = verificationTokenService.createVerificationToken(account);

            // Send verification email
            String verificationUrl = "http://localhost:8080/verify?token=" + token.getToken();
            mailService.sendHtmlMail(account.getEmail(), "Email Verification", verificationUrl);

            model.addAttribute("message",
                    "Account created successfully. Please check your email to verify your account.");
            return "Customer_Login_Register";
        } catch (Exception e) {
            model.addAttribute("message", "Account creation failed.");
            return "Customer_Login_Register";
        }
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token, Model model) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if (verificationToken == null) {
            model.addAttribute("message", "Invalid verification token.");
            return "error";
        }

        Account account = verificationToken.getAccount();
        account.setStatus(1); // Activate the account
        customerService.saveAccount(account); // Save the updated account

        model.addAttribute("message", "Account verified successfully!");
        return "Customer_Login_Register";
    }
}
