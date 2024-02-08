package org.ies.restcontrollers;

import org.ies.bindings.UnlockAcForm;
import org.ies.bindings.UserAccountForm;
import org.ies.exception.ApplicationException;
import org.ies.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ies.constants.AppConst.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping( value = "/createAccount",produces =  MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserAccountForm form) {
      try {
          Boolean isCreated = accountService.createAccount(form);
          if (isCreated)
              return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
          else
              throw new ApplicationException(FAILED);
      }catch (Exception e){
          throw new ApplicationException(e.getMessage());
      }
    }

    @GetMapping(value="/allusers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAccountForm>> getAllUsers() {
        try {
            List<UserAccountForm> users = accountService.viewAccounts();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch(Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @GetMapping(value="/getuser/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccountForm> getUser(@PathVariable Integer userId) {
        try {
            UserAccountForm user = accountService.getAccountById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @PutMapping(value = "/unlockacc",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> unlockAccount(@RequestBody UnlockAcForm form) {
        try {
            String msg = accountService.unlockAccount(form);
            if (msg.equals(UNLOCKED))
                return new ResponseEntity<>(msg, HttpStatus.OK);
            else
                throw new ApplicationException(msg);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
