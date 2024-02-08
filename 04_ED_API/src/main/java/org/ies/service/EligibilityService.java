package org.ies.service;

import org.ies.bindings.EligibilityResponse;

import java.util.List;

public interface EligibilityService {

    public List<EligibilityResponse> determineEligibility(int caseNo);
}
