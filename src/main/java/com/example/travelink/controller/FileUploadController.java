package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelink.model.Account;
import com.example.travelink.service.CustomerService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Autowired
    private CustomerService customerService;

    // Thư mục lưu trữ hình ảnh trong static/images
    private static final String UPLOAD_DIR1 = "src/main/resources/static/images/";
    private static final String UPLOAD_DIR2 = "target/classes/static/images/";

    @SuppressWarnings("null")
    @PostMapping("/UploadImageAvatar")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes model) {
        // Kiểm tra loại file và kích thước
        String fileType = file.getContentType();
        if (!(fileType.equals("image/jpeg") || fileType.equals("image/png"))) {
            model.addFlashAttribute("message", "File format not supported. Please upload JPEG or PNG.");
            return "redirect:/CustomerViewAvatar";
        }
        if (file.getSize() > 1048576) { // 1MB
            model.addFlashAttribute("message", "File size exceeds the limit of 1 MB.");
            return "redirect:/CustomerViewAvatar";
        }

        try {
            // Tạo đường dẫn để lưu file
            String fileName = file.getOriginalFilename();
            Path path1 = Paths.get(UPLOAD_DIR1 + fileName);
            Path path2 = Paths.get(UPLOAD_DIR2 + fileName);
            // Lưu file vào thư mục static/images
            byte[] bytes = file.getBytes();
            Files.write(path1, bytes);
            Files.write(path2, bytes);

            // Trả về URL của ảnh để hiển thị trên trang
            String imageUrl = "images/" + fileName;
            model.addFlashAttribute("imageUrl", imageUrl);
            model.addFlashAttribute("messageSuccess", "File uploaded successfully.");
            return "redirect:/CustomerViewAvatar"; // Trả về view upload với URL của ảnh
        } catch (IOException e) {
            e.printStackTrace();
            model.addFlashAttribute("messageFail", "File upload failed.");
            return "redirect:/CustomerViewAvatar";
        }
    }

    @GetMapping("/CustomerViewAvatar")
    public String customerViewAvatar(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("customer");
        model.addAttribute("customer", account);
        return "Customer_View_Avatar"; // Trả về trang home sau khi login thành công
    }

    @PostMapping("/CustomerUpdateAvatar")
    public String customerUpdateAvatar(@RequestParam("imageUrl") String imageUrl, RedirectAttributes redirectAttributes,
            HttpSession session) {
        Account account = (Account) session.getAttribute("customer");
        account.setAvatarUrl(imageUrl);
        customerService.updateAvatarUrl(account.getEmail(), imageUrl);
        redirectAttributes.addFlashAttribute("messageSuccess", "Upload Avatar Successfully.");
        return "redirect:/CustomerViewAvatar";
    }

}