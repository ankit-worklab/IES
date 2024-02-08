package org.ies.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class UserResponse {
    private  String fullName;
    private String emailId;
    private String mobileNo;
    private String gender;
    private Long ssn;
    private LocalDate dob;
    private String role;

}
