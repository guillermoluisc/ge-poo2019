/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public interface RegistrationService {
    
    public List<Registration> registrations();
    
    public void create(Long eventId, User user) throws Exception;
    
    public Registration find(Long id);
    
    public Registration update(Long id,Registration registration);

    public void delete(Long id);
}
