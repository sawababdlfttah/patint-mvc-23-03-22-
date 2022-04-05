package com.emsi.patintmvc.repositories;

import com.emsi.patintmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PatientRepository extends JpaRepository<Patient,Long> {
//Page<Patient> findByNomContains(String kw, Pageable pageable);

}

