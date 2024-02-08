package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Plan_Holder")
@Data
public class PlanSelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;
    private String planName;
    @ManyToOne
    @JoinColumn
    private  ApplicantEntity applicant;
}
