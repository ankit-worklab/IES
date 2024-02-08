package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
@Table(name="Applicant_Kids_Dtls")
public class KidsInfoEntity implements Serializable {
    @Id
    @Column(name="case_no")
    private int caseNo;
    private  String name;
    private int age;
    private long ssn;
    @OneToOne
    @MapsId
    @JoinColumn(name="case_no")
    private ApplicantEntity applicant;

}
