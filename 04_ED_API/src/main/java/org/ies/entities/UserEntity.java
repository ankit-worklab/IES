package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="IES_USER")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String emailId;
    private String mobileNo;
    private String gender;
    private LocalDate dob;
    private Long ssn;
    private String role;
    private char activeSwitch;
    private String password;
    private String status;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
   // @JoinColumn(name="created_by")
    private List<ApplicantEntity> app;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId && ssn == that.ssn && Objects.equals(fullName, that.fullName) && Objects.equals(emailId, that.emailId) && Objects.equals(mobileNo, that.mobileNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fullName, emailId, mobileNo, ssn);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", ssn=" + ssn +
                ", activeSwitch=" + activeSwitch +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
