package org.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountForm {

    private  String fullName;
    private String emailId;
    private String mobileNo;
    private String gender;
    private Long ssn;
    private LocalDate dob;
    private String role;
}
