package org.ies.service;

import org.ies.bindings.PlanForm;
import org.ies.entities.PlanEntity;
import org.ies.repository.PlansRepository;
import org.ies.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service

public class PlanServiceImp implements PlanService {
    @Autowired
    PlansRepository repository;
    @Autowired
    UserRepository user;
    @Transactional(readOnly = false)
    @Override
    public boolean createPlan(PlanForm form,Integer userId) {
        PlanEntity entity = new PlanEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setCreatedBy(user.findById(userId).get());
        entity.setStatus('Y');
        Integer id = repository.save(entity).getPlanId();
        return id!=0;
    }

    @Override
    public List<PlanForm> viewPlans() {
        List<PlanEntity> entities = repository.findAll();
        List<PlanForm> plans = new ArrayList<>();
        entities.stream().forEach(entity -> {
            PlanForm plan = new PlanForm();
            BeanUtils.copyProperties(entity, plan);
            plans.add(plan);
        });
        return plans;
    }

    @Override
    public PlanForm getPlanById(int planId) {
        PlanForm form = new PlanForm();
         PlanResponse response = repository.findByPlanId(planId, PlanResponse.class);
         BeanUtils.copyProperties(response,form);
         return form;
    }
    @Transactional(readOnly = false)
    @Override
    public boolean changePlanStatus(int planId, Character status) {
        Integer rowCount = repository.updatePlanStatus(status,planId);
        return rowCount!=0;
    }
}
