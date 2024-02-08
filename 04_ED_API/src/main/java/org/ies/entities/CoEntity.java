package org.ies.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="IES_CO")
public class CoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;
    @ManyToOne
    @JoinColumn(name="case_no")
    private ApplicantEntity caseNo;
    @ManyToOne
    @JoinColumn(name="elgb_id")
    private EligibleInfoEntity ElgbId;
    private LocalDate printDate;
    private LocalDate createdDate;
    private String status;
}
