package org.ies.repository;

import org.ies.entities.ApplicantEntity;
import org.ies.entities.EducationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<EducationInfoEntity,Integer> {

public EducationInfoEntity findByApplicant(ApplicantEntity applicant);
}

