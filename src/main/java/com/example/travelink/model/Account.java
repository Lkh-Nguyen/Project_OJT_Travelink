package com.example.travelink.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_ID") // ánh xạ với tên cột trong bảng
    private int accountId;  // Đổi thành camelCase

    @Column(name = "Email", nullable = false) // cột không được null
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "CMND") // Thay đổi thành ID
    private String cmnd;

    @Column(name = "Name", nullable = false) // cột không được null
    private String name;

    @Column(name = "Gender") // Giới tính
    private String gender;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "AvatarURL")
    private String avatarUrl;

    @Column(name = "Address")
    private String address;

    @Column(name = "Role", nullable = false) // cột không được null
    private int role;

    @Column(name = "Status", nullable = false) // cột không được null
    private int status;

}