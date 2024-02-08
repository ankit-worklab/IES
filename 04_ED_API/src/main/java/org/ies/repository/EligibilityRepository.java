package org.ies.repository;

import org.ies.entities.EligibleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EligibilityRepository extends JpaRepository<EligibleInfoEntity,Integer> {


}
