package org.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanForm {

    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
}
