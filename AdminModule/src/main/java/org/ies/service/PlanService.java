package org.ies.service;

import org.ies.bindings.PlanForm;

import java.util.List;

public interface PlanService {

    public boolean createPlan(PlanForm form,Integer userId);
    public List<PlanForm> viewPlans();

    public PlanForm getPlanById(int planId);

    public boolean changePlanStatus(int planId, Character status);
}
