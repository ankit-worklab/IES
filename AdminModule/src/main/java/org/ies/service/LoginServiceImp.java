package org.ies.service;

import org.ies.bindings.DashboradCard;
import org.ies.bindings.ForgetPwdForm;
import org.ies.bindings.LoginForm;
import org.ies.entities.UserEntity;
import org.ies.repository.UserRepository;
import org.ies.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

import static org.ies.constants.AppConst.*;
@Service
public class LoginServiceImp implements LoginService{
   @Autowired
    UserRepository userRepository;
   @Autowired
   EmailUtils emailUtils;
    @Override
    public String loginAccount(LoginForm form) {
        UserEntity entity = userRepository.findByUserIdAndPasswordLike(form.getUserId(), form.getPassword());
        if (entity != null) {
            if('Y' == entity.getActiveSwitch() &&
                    UNLOCKED.equals(entity.getStatus()) ){
                return SUCCESS;
        }else{
                return ACC_LOCKED;
            }
    }
        return INVALID_CRED;
    }
    @Transactional(readOnly = true)
    @Override
    public boolean changePassword(ForgetPwdForm form)  {
        UserEntity entity = userRepository.findByEmailId(form.getEmailId());
        if (entity != null && !form.getNewPassword().equals(null)) {
            entity.setPassword(form.getNewPassword());
            userRepository.save(entity);
            String body = new AccountServiceImp().readEmailBody(PWD_BODY_FILE,entity);
            return emailUtils.sendEmail(RECOVER_SUB,body,entity.getEmailId());
        } else {
            return false;
        }
    }

    @Override
    public DashboradCard viewDashboard() {
        return null;
    }
}
