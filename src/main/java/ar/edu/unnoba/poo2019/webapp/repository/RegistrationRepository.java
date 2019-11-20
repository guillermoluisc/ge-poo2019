/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.repository;

import ar.edu.unnoba.poo2019.webapp.model.Registration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author root
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long>{
    
    public List<Registration> findByEventId(long id);
    
}
