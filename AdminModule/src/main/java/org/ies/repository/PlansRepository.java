package org.ies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ies.entities.PlanEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlansRepository extends JpaRepository<PlanEntity,Integer> {

    //public <T> List<T> findAll(Class<T> classType);

    public <T> T findByPlanId(Integer id, Class<T> classType);

    @Modifying
    @Query("update PlanEntity e set e.status= :status where e.planId= :planId")
    public Integer updatePlanStatus(@Param("status")Character status,@Param("planId") Integer planId);


}
