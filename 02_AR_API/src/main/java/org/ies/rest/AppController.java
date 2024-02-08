package org.ies.rest;

import org.ies.Exception.AppRegistrationException;
import org.ies.bindings.AppForm;
import org.ies.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class AppController {
    @Autowired
    AppService appService;
    @PostMapping("/registerApp/{userId}")
    public ResponseEntity<String> registerApp(@RequestBody AppForm form , @PathVariable("userId") int userId){

        try{
            String response = appService.registerApp(form,userId);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new AppRegistrationException(e.getMessage());
        }
    }
    @GetMapping("/viewapplicants")
    public ResponseEntity<List<AppForm>> getAllApplicants(){
        try{
            List<AppForm> applicants = appService.viewApp();
            return new ResponseEntity<>(applicants,HttpStatus.OK);
        }catch (Exception e){
            throw new AppRegistrationException(e.getMessage(),e);
        }
    }
}
