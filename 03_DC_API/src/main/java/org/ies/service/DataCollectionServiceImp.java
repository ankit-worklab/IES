package org.ies.service;

import org.ies.bindings.EducationInfoForm;
import org.ies.bindings.IncomeForm;
import org.ies.bindings.KidsInfoForm;
import org.ies.bindings.PlanSelForm;
import org.ies.entities.EducationInfoEntity;
import org.ies.entities.IncomeInfoEntity;
import org.ies.entities.KidsInfoEntity;
import org.ies.entities.PlanSelEntity;
import org.ies.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataCollectionServiceImp implements DataCollectionService {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    KidsRepository kidsRepository;
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    PlanSelectRepository planSelectRepository;
    @Override
    public List<String> getDistinctPlanNames() {
        List<String> disPlanNames=new ArrayList<>();
       List<Object> planNames = planRepository.getDistinctPlanNames();
        planNames.stream().map(plan->(String)plan).forEach(disPlanNames::add);
        return disPlanNames;
    }

    @Override
    @Transactional
    public boolean saveEducationDtls(EducationInfoForm form,int caseNo) {
        EducationInfoEntity entity = new EducationInfoEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setApplicant(applicantRepository.findById(caseNo).get());
        EducationInfoEntity savedEntity = educationRepository.save(entity);
        return savedEntity.getCaseNo() !=0;
    }

    @Override
    @Transactional
    public boolean saveIncomeDtls(IncomeForm form,int caseNo) {
        IncomeInfoEntity entity = new IncomeInfoEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setApplicant(applicantRepository.findById(caseNo).get());
        IncomeInfoEntity savedEntity = incomeRepository.save(entity);
        return savedEntity.getCaseNo()!=0;
    }

    @Override
    @Transactional
    public boolean saveKidsDtls(KidsInfoForm form,int caseNo) {
        KidsInfoEntity entity = new KidsInfoEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setApplicant(applicantRepository.findById(caseNo).get());
        KidsInfoEntity savedEntity = kidsRepository.save(entity);
        return savedEntity.getCaseNo() != 0;
    }

    @Override
    @Transactional
    public boolean saveSelectedPlan(PlanSelForm form,int caseNo) {
        PlanSelEntity entity = new PlanSelEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setApplicant(applicantRepository.findById(caseNo).get());
        PlanSelEntity savedEntity = planSelectRepository.save(entity);

        return savedEntity.getSno() != 0;
    }
}
