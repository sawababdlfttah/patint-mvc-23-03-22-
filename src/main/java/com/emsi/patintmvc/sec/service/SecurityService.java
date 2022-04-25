package com.emsi.patintmvc.sec.service;

import com.emsi.patintmvc.sec.entities.AppRole;
import com.emsi.patintmvc.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username ,String password,String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
