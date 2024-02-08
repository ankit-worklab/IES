package org.ies.repository;

import org.ies.entities.ApplicantEntity;
import org.ies.entities.EducationInfoEntity;
import org.ies.entities.KidsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KidsRepository extends JpaRepository<KidsInfoEntity,Integer> {

    public List<KidsInfoEntity> findByApplicant(ApplicantEntity applicant);
}
