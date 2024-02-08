package org.ies.service;

import org.ies.bindings.DashboradCard;
import org.ies.bindings.ForgetPwdForm;
import org.ies.bindings.LoginForm;

import java.io.FileNotFoundException;

public interface LoginService {

    public String loginAccount(LoginForm form);
    public boolean changePassword(ForgetPwdForm form) ;

    public DashboradCard viewDashboard();


}
