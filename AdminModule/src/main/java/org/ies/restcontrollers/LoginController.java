package org.ies.restcontrollers;

import org.apache.coyote.Response;
import org.ies.bindings.DashboradCard;
import org.ies.bindings.ForgetPwdForm;
import org.ies.bindings.LoginForm;
import org.ies.bindings.UserAccountForm;
import org.ies.entities.UserEntity;
import org.ies.exception.ApplicationException;
import org.ies.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.ies.constants.AppConst.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm form) {
        try {
            String msg = loginService.loginAccount(form);
            if (msg.equals(SUCCESS)) {
                return new ResponseEntity<>("redirect:/dashboard",HttpStatus.PERMANENT_REDIRECT);
            }else
                throw new ApplicationException(msg);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }
    @PostMapping("/updatepwd")
    public ResponseEntity<String> forgetPwd(@RequestBody ForgetPwdForm form){
       try {
           Boolean isChanged = loginService.changePassword(form);
           if(isChanged)
               return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
           else
               throw new ApplicationException(INVALID_CRED);
       }catch (Exception e){
           throw new ApplicationException(e.getMessage());
       }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboradCard> openDashboard(@RequestBody UserAccountForm user){
     try{
         return new ResponseEntity<>(loginService.viewDashboard(), HttpStatus.OK);
     }catch(Exception e){
         throw new ApplicationException(e.getMessage());
     }
    }
}
