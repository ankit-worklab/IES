package org.ies.service;

import org.ies.bindings.EligibilityResponse;
import org.ies.entities.*;
import org.ies.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EligibilityServiceImp implements EligibilityService {
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    EligibilityRepository eligibilityRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    KidsRepository kidsRepository;
    @Autowired
    PlanSelectRepository planSelectRepository;

    @Override
    public List<EligibilityResponse> determineEligibility(int caseNo) {

        ApplicantEntity applicant = applicantRepository.findById(caseNo).get();

        List<String> planNames = new ArrayList<>();
        planSelectRepository.findByApplicant(applicant)
                .stream().forEach(plan -> planNames.add(plan.getPlanName()));

        EducationInfoEntity educationInfo = educationRepository.findByApplicant(applicant);

        IncomeInfoEntity incomeInfo = incomeRepository.findByApplicant(applicant);
        double totalIncome = incomeInfo.getPropertyIncome() + incomeInfo.getMonSalaryIncome() + incomeInfo.getRentIncome();
        List<KidsInfoEntity> kidsInfo = kidsRepository.findByApplicant(applicant);


        List<EligibilityResponse> eligibilityResponseList = null;

        for (String planName : planNames) {
            EligibleInfoEntity savedEntity = null;
            if ("SNAP".equals(planName)) {
                if (incomeInfo.getMonSalaryIncome() < 300) {
                    savedEntity = saveEntity(planName, "Approved", applicant, 500, "NA",
                            LocalDate.now(), LocalDate.now().plusMonths(12));
                } else {
                    savedEntity = saveEntity(planName, "Denied", applicant, 0.0, "out of salary criteria",
                            null, null);
                }
                if (savedEntity.getEligbilityId() != 0) {
                    eligibilityResponseList.add(prepareResponse(savedEntity));
                }
            }

            if ("CCAP".equals(planName)) {
                if (incomeInfo.getMonSalaryIncome() < 300 && kidsInfo.stream().allMatch(kid -> kid.getAge() < 16)) {

                    savedEntity = saveEntity(planName, "Approved", applicant, 1500, "NA",
                            LocalDate.now(), LocalDate.now().plusMonths(12));
                } else {
                    savedEntity = saveEntity(planName, "Denied", applicant, 0.0, "out of kids criteria",
                            null, null);
                }
                if (savedEntity.getEligbilityId() != 0) {
                    eligibilityResponseList.add(prepareResponse(savedEntity));
                }
            }

            if ("Medicaid".equals(planName)) {
                if (totalIncome < 300) {

                    savedEntity = saveEntity(planName, "Approved", applicant, 1000, "NA",
                            LocalDate.now(), LocalDate.now().plusMonths(12));
                } else {
                    savedEntity = saveEntity(planName, "Denied", applicant, 0.0, "out of salary criteria",
                            null, null);
                }
                if (savedEntity.getEligbilityId() != 0) {
                    eligibilityResponseList.add(prepareResponse(savedEntity));
                }
            }

            if ("Medicare".equals(planName)) {

                if (applicant.getDob().isBefore(LocalDate.of(2024, 01, 01).minusYears(65))) {

                    savedEntity = saveEntity(planName, "Approved", applicant, 2000, "NA",
                            LocalDate.now(), LocalDate.now().plusMonths(12));
                } else {
                    savedEntity = saveEntity(planName, "Denied", applicant, 0.0, "out of age criteria",
                            null, null);
                }
                if (savedEntity.getEligbilityId() != 0) {
                    eligibilityResponseList.add(prepareResponse(savedEntity));
                }
            }

        }
        return eligibilityResponseList;
    }

    private EligibleInfoEntity saveEntity(EligibleInfoEntity entity){
        return eligibilityRepository.save(entity);
    }
    private EligibleInfoEntity saveEntity(String planNm,String status,ApplicantEntity applicant,
                                          double amt,String denial,LocalDate startDt,LocalDate endDt){
        EligibleInfoEntity entity =null;
       entity= EligibleInfoEntity.of().planName(planNm).planStatus(status).
                applicant(applicant).benefitAmt(amt).denialReason(denial).planStartDate(startDt)
                .planEndDate(endDt).build();
       return eligibilityRepository.save(entity);

    }
    private EligibilityResponse prepareResponse(EligibleInfoEntity entity){
        EligibilityResponse response = new EligibilityResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
