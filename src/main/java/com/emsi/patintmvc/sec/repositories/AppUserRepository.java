package com.emsi.patintmvc.sec.repositories;

import com.emsi.patintmvc.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername (String username);
}
