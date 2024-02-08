package org.ies.repository;

import org.ies.entities.ApplicantEntity;
import org.ies.entities.EligibleInfoEntity;
import org.ies.entities.PlanSelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanSelectRepository extends JpaRepository<PlanSelEntity,Integer> {

    public List<PlanSelEntity> findByApplicant(ApplicantEntity applicant);
}
