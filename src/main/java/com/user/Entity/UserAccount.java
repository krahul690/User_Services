package com.user.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "User_TBL")
public class UserAccount {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer contactId;        // Matches with `contactId` in UserForm

    @Column(name = "contact_name")
    private String contactName;        // Matches with `contactName` in UserForm

    @Column(name = "contact_email")
    private String contactEmail;       // Matches with `contactEmail` in UserForm

    @Column(name = "contact_number")
    private Long contactNumber;        // Matches with `contactNumber` in UserForm

    @Column(name = "user_Gender")
    private String gender;      // Matches with `gender` in UserForm

    @Column(name = "contact_activesw")
    private String activeSw;        // Matches with `activeSw` in UserForm

    @Column(name = "contact_createdate")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @CreationTimestamp
    private LocalDate creatDate;    // Matches with `creatDate` in UserForm

    @Column(name = "contact_updatedate")
    @UpdateTimestamp
    private LocalDate updateDate;   // Matches with `updateDate` in UserForm
}
