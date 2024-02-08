package org.ies.repository;

import org.ies.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {

    @Query("select distinct(p.planName) from PlanEntity as p")
    public List<Object> getDistinctPlanNames();
}
