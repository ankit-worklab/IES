package org.ies.rest;

import org.ies.bindings.EducationInfoForm;
import org.ies.bindings.IncomeForm;
import org.ies.bindings.KidsInfoForm;
import org.ies.bindings.PlanSelForm;
import org.ies.exception.ApplicationException;
import org.ies.service.DataCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ies.constants.AppConstants.FAILED;
import static org.ies.constants.AppConstants.SUCCESS;

@RestController
@RequestMapping("/datacollection")
public class DataCollectionController {
    @Autowired
    DataCollectionService dataCollectionService;
    @GetMapping("/plannames")
    public ResponseEntity<List<String>> getPlanNames(){
        try{
            return new ResponseEntity<>(dataCollectionService.getDistinctPlanNames(), HttpStatus.OK);
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }

    @PostMapping("/saveeducationinfo/{caseNo}")
    public ResponseEntity<String> saveEducationInfo(@RequestBody EducationInfoForm form,@PathVariable("caseNo") int caseNo){
        try{     boolean isSaved= dataCollectionService.saveEducationDtls(form,caseNo);
            if(isSaved)
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
            else
                throw new ApplicationException(FAILED);
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }

    @PostMapping("/saveincomeinfo/{caseNo}")
    public ResponseEntity<String> saveIncomeDtls(@RequestBody IncomeForm form,@PathVariable("caseNo") int caseNo ){
        try{
            boolean isSaved=dataCollectionService.saveIncomeDtls(form,caseNo);
            if(isSaved)
                return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
            else
                throw new ApplicationException(FAILED);
        } catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }
    @PostMapping("/savekidsinfo/{caseNo}")
    public ResponseEntity<String> saveKidsInfo(@RequestBody KidsInfoForm form,@PathVariable("caseNo") int caseNo){
        try {
            boolean isSaved = dataCollectionService.saveKidsDtls(form,caseNo);
            if (isSaved)
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            else
                throw new ApplicationException(FAILED);
        }catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }
    @PostMapping("/saveplan/{caseNo}")
    public ResponseEntity<String> saveSelectedPlan(@RequestBody PlanSelForm form,@PathVariable("caseNo") int caseNo ){
        try{
            boolean isSaved = dataCollectionService.saveSelectedPlan(form,caseNo);
            if(isSaved){
                 return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }  else
                 throw new ApplicationException(FAILED);
        } catch (Exception e){
                throw new ApplicationException(e.getMessage());
        }
    }


}
