package org.ies.restcontrollers;

import org.ies.bindings.PlanForm;
import org.ies.exception.ApplicationException;
import org.ies.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ies.constants.AppConst.*;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    PlanService planService;

    @PostMapping(value = "/createplan/{userId}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createPlan(@RequestBody PlanForm form, @PathVariable("userId") int userId) {
        try {
            Boolean isCreated = planService.createPlan(form, userId);
            if (isCreated)
                return new ResponseEntity(SUCCESS, HttpStatus.CREATED);
            else {
                throw new ApplicationException(FAILED);
            }
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @GetMapping(value = "/getplans")
    public ResponseEntity<List<PlanForm>> getAllPlans() {
        try {
            return new ResponseEntity<>(planService.viewPlans(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @GetMapping(value = "/getplan/{planId}")
    public ResponseEntity<PlanForm> getPlan(@PathVariable("planId") Integer planId) {
        try {
            return new ResponseEntity<>(planService.getPlanById(planId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @PutMapping(value = "/changeplanstatus/{planId}/{status}")
    public ResponseEntity<String> changePlanStatus(@PathVariable("planId") Integer planId, @PathVariable("status") Character status) {
        try {
            boolean isChanged = planService.changePlanStatus(planId, status);
            if (isChanged)
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            else
                throw new ApplicationException(INVALID_CRED);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
