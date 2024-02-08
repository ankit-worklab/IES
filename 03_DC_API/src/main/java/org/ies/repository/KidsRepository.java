package org.ies.repository;

import org.ies.entities.KidsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidsRepository extends JpaRepository<KidsInfoEntity,Integer> {
}
