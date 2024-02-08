package org.ies.bindings;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EligibilityResponse {

    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private double benefitAmt;
    private String denialReason;

}
