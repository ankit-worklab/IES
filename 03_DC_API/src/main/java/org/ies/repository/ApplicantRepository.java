package org.ies.repository;

import org.ies.entities.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity,Integer> {

}
