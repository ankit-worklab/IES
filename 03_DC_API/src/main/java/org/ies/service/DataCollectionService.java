package org.ies.service;

import org.ies.bindings.EducationInfoForm;
import org.ies.bindings.IncomeForm;
import org.ies.bindings.KidsInfoForm;
import org.ies.bindings.PlanSelForm;

import java.util.List;

public interface DataCollectionService {

    List<String> getDistinctPlanNames();

    boolean saveEducationDtls(EducationInfoForm form,int caseNo);
    boolean saveIncomeDtls(IncomeForm form,int caseNo);
    boolean saveKidsDtls(KidsInfoForm form,int caseNo);
    boolean saveSelectedPlan(PlanSelForm form,int caseNo);
}
