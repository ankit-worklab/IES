package org.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnlockAcForm {

    private String emailId;
    private String password;
    private String newPassword;
    private String confirmPassword;

}
