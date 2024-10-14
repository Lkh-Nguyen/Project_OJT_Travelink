
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Servlet;

/**
 *
 * @author DUYAN
 */
import com.travelink.Database.AccountDB;
import com.travelink.Database.BillDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.RefundingReservationDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.MonthlyPayment;
import com.travelink.Model.Reservation;
import com.travelink.Model.Bill;
import com.travelink.Model.RefundingReservation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DUYAN
 */
public class SendEmail {

    public void sendMail(String mail, String name) {

        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage mgs = new MimeMessage(session);
        try {

            mgs.setFrom(new InternetAddress(fromEmail));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mgs.setSubject("Welcome to FiveStars Cinema: Your Ticket to Entertainment Excellence!");
            mgs.setText("FiveStars Cinema Team"
                    + "\n"
                    + "Dear " + name + ",\n"
                    + "\n"
                    + "Welcome to FiveStars Cinema! We are thrilled to have you on board and thank you for registering with us. Your journey to exceptional cinematic experiences begins now!\n"
                    + "\n"
                    + "At FiveStars Cinema, we strive to redefine the movie-going experience by offering top-notch amenities, unparalleled customer service, and a diverse selection of films to cater to all tastes. Whether you're a fan of action-packed blockbusters, heartwarming dramas, or captivating documentaries, we have something for everyone.\n"
                    + "\n"
                    + "Here are a few highlights of what you can expect as a member of FiveStars Cinema:\n"
                    + "\n"
                    + "State-of-the-Art Facilities: Our theaters are equipped with cutting-edge technology to ensure the highest quality audio and visual experience. Sit back, relax, and immerse yourself in the magic of the big screen.\n"
                    + "\n"
                    + "Exclusive Events and Screenings: As a valued member, you'll have access to exclusive events, premieres, and special screenings. Be the first to witness the latest releases and participate in exciting promotions.\n"
                    + "\n"
                    + "Comfort and Convenience: Say goodbye to long queues and crowded lobbies. With our user-friendly online booking system, you can easily reserve your seats in advance and skip the hassle. Plus, enjoy amenities like comfortable seating and gourmet concessions for the ultimate movie-going experience.\n"
                    + "\n"
                    + "Personalized Recommendations: Let us take the guesswork out of movie selection. Our recommendation engine analyzes your viewing preferences to suggest films tailored to your tastes. Discover new favorites and explore hidden gems with ease.\n"
                    + "\n"
                    + "Rewards and Loyalty Program: Earn points with every purchase and unlock exciting rewards. Whether it's free tickets, concession discounts, or exclusive merchandise, your loyalty pays off with FiveStars Cinema.\n"
                    + "\n"
                    + "We are committed to providing you with unforgettable moments and unforgettable movies. So sit back, relax, and get ready to embark on an extraordinary cinematic journey with FiveStars Cinema.\n"
                    + "\n"
                    + "Once again, welcome to the FiveStars family! If you have any questions or need assistance, feel free to reach out to our dedicated customer support team at [email protected]\n"
                    + "\n"
                    + "Lights, camera, action!\n"
                    + "\n"
                    + "Warm regards,\n"
                    + "\n"
                    + "From An,\n"
                    + "FiveStars Cinema Team");
            Transport.send(mgs);
            System.out.println("Sent Magess");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendRegisterToken(String mail, int token) {

        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage mgs = new MimeMessage(session);
        try {

            mgs.setFrom(new InternetAddress(fromEmail));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mgs.setSubject("Request to verify account !!!");
            mgs.setText("Your verification code is: " + token);
            Transport.send(mgs);
            System.out.println("Sent Magess");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendForgotPassword(String mail, String token) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage mgs = new MimeMessage(session);
        try {

            mgs.setFrom(new InternetAddress(fromEmail));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mgs.setSubject("Request to reset Password.");
            mgs.setText("Your new Password is set default to: " + token);
            Transport.send(mgs);
            System.out.println("Sent Magess");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendHotelEmail(String mail, String msg) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage mgs = new MimeMessage(session);
        try {

            mgs.setFrom(new InternetAddress(fromEmail));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mgs.setSubject("Pending Hotel Host Account on Travelink Platform");
            mgs.setText("Your hotel has been " + msg + " by Admin");
            Transport.send(mgs);
            System.out.println("Sent Magess");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendSuccessReservation(String mail, Reservation reservation) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Account account = AccountDB.getAccount(mail);
        List<Bill> listBill = BillDB.getBillByCustomerIDAndReservationID(account.getAccount_ID(), reservation.getReservationID());

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your Travelink Reservation Confirmation");

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Dear ").append(account.getName()).append(",\n\n");
            emailContent.append("Thank you for booking with Travelink! We are pleased to confirm your reservation with the following details:\n\n");

            for (Bill bill : listBill) {
                emailContent.append("Reservation ID: ").append(bill.getReservationID()).append("\n")
                        .append("Hotel Name: ").append(bill.getHotel_Name()).append("\n")
                        .append("Room Name: ").append(bill.getRoom_Name()).append("\n")
                        .append("Check-In Date: ").append(bill.getCheckInDate()).append("\n")
                        .append("Check-Out Date: ").append(bill.getCheckOutDate()).append("\n")
                        .append("Number of Guests: ").append(bill.getNumber_of_guest()).append("\n")
                        .append("Total Price: ").append(bill.getTotal_price()).append(" VND\n")
                        .append("Status: ").append(bill.getStatus()).append("\n\n");
            }

            emailContent.append("If you have any questions or need to make changes to your reservation, please do not hesitate to contact us at [travelink517@gmail.com/0763905056].\n\n")
                    .append("We look forward to welcoming you soon!\n\n")
                    .append("Best regards,\n")
                    .append("Travelink Team");

            message.setText(emailContent.toString());
            Transport.send(message);
            System.out.println("Sent reservation confirmation email!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendAccountStatusEmail(String mail, boolean isLocked) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            String subject, body;
            if (isLocked) {
                subject = "Your Travelink Account Has Been Locked";
                body = "Dear User,\n\nYour Travelink account has been locked due to suspicious activities or violation of our terms of service. Please contact our support team if you believe this is a mistake.\n\nBest regards,\nTravelink Team";
            } else {
                subject = "Your Travelink Account Has Been Unlocked";
                body = "Dear User,\n\nYour Travelink account has been unlocked. You can now access your account and continue using our services.\n\nBest regards,\nTravelink Team";
            }
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Sent account status email!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendHotelStatusEmail(String mail, boolean isLocked) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            String subject, body;
            if (isLocked) {
                subject = "Your Hotel Has Been Locked";
                body = "Dear User,\n\nYour Hotel has been locked due to suspicious activities or violation of our terms of service. Please contact our support team if you believe this is a mistake.\n\nBest regards,\nTravelink Team";
            } else {
                subject = "Your Hotel Has Been Unlocked";
                body = "Dear User,\n\nYour Hotel has been unlocked. Now Your Hotel's status is ACTIVE.\n\nBest regards,\nTravelink Team";
            }
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Sent hotel status email!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendSuccessPayMonthlyPayment(MonthlyPayment monthlyPayment) {
        Hotel hotel = HotelDB.getHotelByID(monthlyPayment.getHotel_ID());
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = hotel.getEmail();
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Monthly Payment Confirmation");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
            String formattedPaymentTime = monthlyPayment.getPaymentTime().format(formatter);

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Dear Hotel Partner,\n\n")
                    .append("We are pleased to inform you that your monthly payment has been successfully processed.\n\n")
                    .append("Payment Details:\n")
                    .append("Month: ").append(monthlyPayment.getMonth()).append("\n")
                    .append("Year: ").append(monthlyPayment.getYear()).append("\n")
                    .append("Amount: ").append(monthlyPayment.getAmount()).append(" VND\n")
                    .append("Payment Time: ").append(formattedPaymentTime).append("\n\n")
                    .append("If you have any questions or need further assistance, please do not hesitate to contact us.\n\n")
                    .append("Best regards,\n")
                    .append("Travelink Team");

            message.setText(emailContent.toString());
            Transport.send(message);
            System.out.println("Sent monthly payment confirmation email!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMailRefundingSuccess(String mail, int reservation_ID) {
        final String username = "travelink517@gmail.com";
        final String password = "klfb cnic dgfd fcqv";
        String fromEmail = "travelink517@gmail.com";
        String toEmail = mail;
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Account account = AccountDB.getAccount(mail);
        RefundingReservation refundingReservation = RefundingReservationDB.getPendingCancelReservationByReservationID(reservation_ID);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your Refund Has Been Successfully Processed");

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("<h1 style=\"color : blue\">We have successfully refunded your payment</h1>");
            emailContent.append("<h3 style=\"color : black\"><strong>Information Refunding:</strong></h3>");
            emailContent.append("<p style=\"color : black\">Name customer: ").append(account.getName()).append("</p>");
            emailContent.append("<p style=\"color : black\">Date cancel: ").append(refundingReservation.getCancelDate()).append("</p>");
            emailContent.append("<p style=\"color : black\">Date refunding: ").append(refundingReservation.getRefundTime()).append("</p>");
            emailContent.append("<p style=\"color : black\">Status refunding: ").append(refundingReservation.getStatus()).append("</p>");
            emailContent.append("<p style=\"color : black\">Total money refunding: $").append(refundingReservation.getAmount()).append("</p><br>");
            emailContent.append("<p style=\"color : black\">If you have any questions or need further assistance, please do not hesitate to contact us.</p>");
            emailContent.append("<p style=\"color : black\" >Best regards,<br>Travelink Team</p>");

            message.setContent(emailContent.toString(), "text/html");

            Transport.send(message);
            System.out.println("Sent refund confirmation email!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendEmail sendEmail = new SendEmail();
        // create a sample MonthlyPayment object for testing
        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setHotel_ID(1); // set a valid hotel_ID
        monthlyPayment.setMonth(6);
        monthlyPayment.setYear(2024);
        monthlyPayment.setAmount(1000000);
        monthlyPayment.setPaymentTime(LocalDateTime.now());

        sendEmail.sendSuccessPayMonthlyPayment(monthlyPayment);
    }
}
