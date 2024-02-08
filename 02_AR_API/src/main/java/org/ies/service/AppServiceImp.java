package org.ies.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.ies.bindings.AppForm;
import org.ies.entities.AppEntity;
import org.ies.repository.AppRepository;
import org.ies.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class AppServiceImp implements AppService{
    @Autowired
    AppRepository appRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public String registerApp(AppForm form,int userId) {

        //write logic for verifying the citizen based on ssn through restcall

        AppEntity entity = new AppEntity();
        BeanUtils.copyProperties(form,entity);
        entity.setUser(userRepository.findById(userId).get());
        AppEntity savedEntity = appRepository.save(entity);
        if(savedEntity.getCaseNo()!=0){
            return "saved";
        }
        return "invalid Credential";
    }

    @Override
    public List<AppForm> viewApp() {
        List<AppEntity> entities = appRepository.findAll();
        List<AppForm> applicants = new ArrayList<>();
        entities.stream().forEach(entity->{
            AppForm applicant = new AppForm();
            BeanUtils.copyProperties(entity,applicant);
            applicants.add(applicant);
        });
        return applicants;
    }
}
