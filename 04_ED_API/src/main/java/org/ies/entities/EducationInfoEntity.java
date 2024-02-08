package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
@Table(name="Applicant_Education_Dtls")
public class EducationInfoEntity implements Serializable {
    @Id
    @Column(name="case_no")
    private int caseNo;
    @Column(name="Degree")
    private String highestDegree;
    @Column(name="compl_year")
    private int completionYear;
    private String university;
    @OneToOne
    @MapsId
    @JoinColumn(name="case_no")
    private ApplicantEntity applicant;
}
