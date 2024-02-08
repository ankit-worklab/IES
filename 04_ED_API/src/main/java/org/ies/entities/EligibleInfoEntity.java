package org.ies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="IES_ELIGBILITY")
public class EligibleInfoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eligbilityId;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private double benefitAmt;
    private String denialReason;
    @ManyToOne
    @JoinColumn(name="applicant")
    private ApplicantEntity applicant;
}
