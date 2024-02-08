package org.ies.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.ies.bindings.UnlockAcForm;
import org.ies.bindings.UserAccountForm;
import org.ies.entities.UserEntity;
import org.ies.entities.UserResponse;
import org.ies.repository.UserRepository;
import org.ies.utils.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.ies.constants.AppConst.*;

@Service
public class AccountServiceImp implements AccountService {
   @Autowired
    EmailUtils emailUtils;
   @Autowired
    UserRepository repository;

    @Override
    @Transactional(readOnly = false)
    public Boolean createAccount(UserAccountForm form)  {
        UserEntity user =new UserEntity();
        BeanUtils.copyProperties(form,user);
        String temPass = pwdGenerator();
        user.setStatus("LOCKED");
        user.setPassword(temPass);
        repository.save(user);

        // send email
        String sub ="User Registration";
        String body=readEmailBody(REG_BODY_FILE,user);
       return emailUtils.sendEmail(sub,body,user.getEmailId());

    }

    @Override
    public UserAccountForm getAccountById(Integer userId) {
        UserResponse userResponse =repository.findByUserId(userId, UserResponse.class);
        UserAccountForm userAccountForm = new UserAccountForm();
        BeanUtils.copyProperties(userResponse,userAccountForm);
        return userAccountForm;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean changeAccountStatus(Integer userId, String status) {
       Integer rowNo= repository.updateAccountStatus(userId,status);
        return rowNo==1;
    }

    @Override
    public List<UserAccountForm> viewAccounts() {

        List<UserEntity> entities=  repository.findAll();
        List<UserAccountForm> users=new ArrayList<>();
        entities.stream().forEach(entity->{
            UserAccountForm user = new UserAccountForm();
            BeanUtils.copyProperties(entity,user);
            users.add(user);
        });
        return users;

    }

    @Override
    @Transactional
    public String unlockAccount(UnlockAcForm form) {

        UserEntity entity = repository.findByEmailId(form.getEmailId());
        if(entity != null) {
            entity.setPassword(form.getPassword());
            entity.setStatus(UNLOCKED);
            repository.save(entity);
            return UNLOCKED;
        }else{
            return INVALID_CRED;
        }
    }

    private String pwdGenerator(){
        String characters = PWD_CHARACTER;
        return  RandomStringUtils.random( 15, characters );
    }

    public String readEmailBody(String fileName,UserEntity user)  {
      //  URL resourceUrl = AccountServiceImp.class.getClassLoader().getResource(fileName);
      try{
//          BufferedReader reader = new BufferedReader(new FileReader(new File("AccountServiceImp.class.getClassLoader().getResourceAsStream(fileName)")));
//        StringBuilder builder = new StringBuilder();
////        try(Stream<String> lines = Files.lines(Paths.get(resourceUrl.getFile()))){
////            lines.forEach(line->{
////                line.replace(FNAME,user.getFullName());
////                line.replace(EMAIL,user.getEmailId());
////                line.replace(PWD,user.getPassword());
////                builder.append(line);
////            });
//          String line=reader.readLine();
//        while(line != null){
//                line.replace(FNAME,user.getFullName());
//                line.replace(EMAIL,user.getEmailId());
//                line.replace(PWD,user.getPassword());
//                builder.append(line);
//             line = reader.readLine();
//        }
           Resource resource = new ClassPathResource(fileName);
           File file = resource.getFile();
           BufferedReader reader = new BufferedReader(new FileReader(file));
           StringBuilder builder = new StringBuilder();
            reader.lines().forEach(line->{
              String line1= line.replace(FNAME,user.getFullName());
              String line2 =  line1.replace(EMAIL,user.getEmailId());
              String line3= line2.replace(PWD,user.getPassword());
               builder.append(line3);

           });
            return builder.toString();

        }catch (Exception e){
          System.out.println("file not found");
            e.printStackTrace();
        }
        return null;
    }

}
