package com.example.travelink.service;


import java.security.SecureRandom;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.travelink.dto.AccountDTO;
import com.example.travelink.email.EmailSender;
import com.example.travelink.model.Account;
import com.example.travelink.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    
    private final CustomerRepository account_Repository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;


    //get List Account
    public List<Account> getAllAccount(){
        return account_Repository.findAll();
    }

    //get List Account by role
    public List<Account> getCustomerByRole(int role){
        return account_Repository.findByRole(role);
    }

    //get List Account by email
    public Account getCustomerByEmail(String email){
        return account_Repository.findByEmail(email);
    }

    
    //register create new Account
    public Account registerNewCustomer(AccountDTO accountDTO){
        if(account_Repository.findByEmail(accountDTO.getEmail()) != null){
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
        
        return account_Repository.save(account);
    }

    //register create google Account
    public Account registerByOAuth(OAuth2User oAuth2User) {
        //Get profile user
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        String address = oAuth2User.getAttribute("address");
        String phone = oAuth2User.getAttribute("phone_number");

        //Check exist
        Account existingAccount = account_Repository.findByEmail(email);
        
        //Return if already register
        if (existingAccount != null) {
            return existingAccount;
        }
        
        // Nếu chưa tồn tại, tạo mới account
        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setName(name);
        newAccount.setPassword(passwordEncoder.encode(generateRandomPassword()));
        newAccount.setCmnd("DefaultCMND");
        newAccount.setGender('M');
        newAccount.setDateOfBirth(null);
        newAccount.setAvatarUrl(picture);
        newAccount.setPhoneNumber(phone);
        newAccount.setAddress(address);
        newAccount.setRole(1); // 1: User, 0: Admin
        newAccount.setStatus(1); // 1: Active

        // Gửi email xác nhận sau khi lưu
        emailSender.sendEmail(newAccount.getEmail(), buildThankYouEmail(newAccount.getName()));
        
        return account_Repository.save(newAccount);
    }


    //Generate random password for Google Login
    private String generateRandomPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }


    //Build form mail
    private String buildThankYouEmail(String name) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "        <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "            <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "                <tbody><tr>\n" +
                "                    <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                        <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                            <tbody><tr>\n" +
                "                                <td style=\"padding-left:10px\">\n" +
                "                                </td>\n" +
                "                                <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                                    <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Thank You for Registering!</span>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </tbody></table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody></table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</tbody></table>\n" +
                "<table role=\"presentation\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "        <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p>\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Thank you for registering and trusting Travelink. We are excited to have you on board!</p>\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">You can start exploring our services by visiting our <a href=\"[YOUR_LINK]\">website</a>.</p>\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">If you have any questions or need assistance, feel free to reach out to our support team.</p>\n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">See you soon!</p>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "</tbody></table>\n" +
                "<div class=\"yj6qo\"></div><div class=\"adL\"></div></div>";
    }
    

}
