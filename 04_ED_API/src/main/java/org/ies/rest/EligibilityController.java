package org.ies.rest;

import org.ies.bindings.EligibilityResponse;
import org.ies.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {
    @Autowired
    EligibilityService eligibilityService;
    @GetMapping("/determine/{caseNo}")
    public ResponseEntity<List<EligibilityResponse>> findEligibility(@PathVariable int caseNo){
        List<EligibilityResponse> responses = eligibilityService.determineEligibility(caseNo);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


}
