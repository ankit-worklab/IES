package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="Applicant_Income_Info")
public class IncomeInfoEntity implements Serializable {
    @Id
    @Column(name="case_no")
    private int caseNo;
    @Column(name="monthly_sal")
    private double monSalaryIncome;
    @Column(name="property_incm")
    private double propertyIncome;
    @Column(name="rent_incm")
    private double rentIncome;
    @OneToOne
    @MapsId
    @JoinColumn(name="case_no")
    private ApplicantEntity applicant;
}
