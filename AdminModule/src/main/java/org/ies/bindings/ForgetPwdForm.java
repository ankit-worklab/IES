package org.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPwdForm {

    private String emailId;
    private String newPassword;
    private String confirmPassword;
}
