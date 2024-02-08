package org.ies.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppForm {

    private String fullName;
    private String emailId;
    private String mobileNo;
    private String gender;
    private LocalDate dob;
    private long ssn;
}
