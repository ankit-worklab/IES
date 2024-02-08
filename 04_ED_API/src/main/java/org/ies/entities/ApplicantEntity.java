package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="IES_Reg_App")
@Data
public class ApplicantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseNo;
    private String fullName;
    private String gender;
    private String emailId;
    private String mobileNo;
    private LocalDate dob;
    private Long ssn;
    @ManyToOne
    @JoinColumn(name="created_by")
    private UserEntity user;

    @OneToOne(mappedBy = "applicant")
    @PrimaryKeyJoinColumn
    private IncomeInfoEntity incomeEntity;

    @OneToOne(mappedBy = "applicant")
    @PrimaryKeyJoinColumn
    private EducationInfoEntity educationEntity;

    @OneToOne(mappedBy = "applicant")
    @PrimaryKeyJoinColumn
    private KidsInfoEntity kidsEntity;


}
