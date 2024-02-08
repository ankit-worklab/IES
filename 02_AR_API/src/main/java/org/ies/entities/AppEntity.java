package org.ies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="IES_Reg_App")
@Data
public class AppEntity {
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
}
