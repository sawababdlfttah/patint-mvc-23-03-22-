package com.emsi.patintmvc.sec.service;

import com.emsi.patintmvc.sec.entities.AppRole;
import com.emsi.patintmvc.sec.entities.AppUser;
import com.emsi.patintmvc.sec.repositories.AppRoleRepository;
import com.emsi.patintmvc.sec.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.security.util.Password;

import java.util.UUID;

@Service
@Slf4j  // permet de loger les informations
// constructor avec parametre
@AllArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

/* ça ou @AllArgsConstructor
    public SecurityServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

 */

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new  RuntimeException("password not match ");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        //UIID permet de genrer chaine de caractere aletatoire
        appUser .setUserId(UUID.randomUUID().toString());
        appUser .setUsername(username);
        appUser .setPassword(hashedPWD);
        appUser .setActive(true);
        AppUser savedAppUser =  appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole= appRoleRepository.findByRoleName(roleName);
        if (appRole!=null)throw new RuntimeException("Role"+roleName+"already exist");
        appRole=new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole= appRoleRepository.save(appRole);
        return savedAppRole;
    }
    @Transactional
    //transactional ????????????? n'a pas besion de faire save  ???
    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null)throw new RuntimeException("Uset not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole!=null)throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
    // c'est pas obligatoir a cause de transactional     appUserRepository.save(appUser);


    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
            AppUser appUser=appUserRepository.findByUsername(username);
            if (appUser!=null)throw new RuntimeException("Uset not found");
            AppRole appRole=appRoleRepository.findByRoleName(roleName);
            if (appRole!=null)throw new RuntimeException("Role not found");
            appUser.getAppRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUserName(String username) {

        return appUserRepository.findByUsername(username);
    }
}
