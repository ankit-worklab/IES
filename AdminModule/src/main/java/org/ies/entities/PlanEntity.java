package org.ies.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="IES_PLANS")
@Data
public class PlanEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
    @Column(columnDefinition = "varchar(1) DEFAULT 'Y'")
    private Character status;
    @ManyToOne
    @JoinColumn(name="created_by")
    private UserEntity createdBy;
    @UpdateTimestamp
    @Column(name="update_date",insertable = false)
    private LocalDate updateDate;
    @CreationTimestamp
    @Column(name="created_date",updatable = false)
    private LocalDate createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEntity that = (PlanEntity) o;
        return Objects.equals(planName, that.planName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planName, startDate, endDate, category);
    }

    @Override
    public String toString() {
        return "PlanEntity{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", updateDate=" + updateDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
