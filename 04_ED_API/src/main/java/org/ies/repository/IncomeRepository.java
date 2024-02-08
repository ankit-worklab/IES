package org.ies.repository;

import org.ies.entities.ApplicantEntity;
import org.ies.entities.IncomeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<IncomeInfoEntity,Integer> {

    public IncomeInfoEntity findByApplicant(ApplicantEntity applicant);

}
