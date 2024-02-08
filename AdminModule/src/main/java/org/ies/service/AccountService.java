package org.ies.service;

import org.ies.bindings.UnlockAcForm;
import org.ies.bindings.UserAccountForm;

import java.io.FileNotFoundException;
import java.util.List;

public interface AccountService {

    public Boolean createAccount(UserAccountForm form) ;
    public UserAccountForm getAccountById(Integer userId);
    public Boolean changeAccountStatus(Integer userId, String status);
    public List<UserAccountForm> viewAccounts();

    String unlockAccount(UnlockAcForm form);
}
