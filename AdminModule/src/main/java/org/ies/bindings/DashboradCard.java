package org.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboradCard {

    private int totalPlans;
    private int citizenApproved;
    private int citizenDenied;
    private double amtDistributed;
    private UserAccountForm user;
}
