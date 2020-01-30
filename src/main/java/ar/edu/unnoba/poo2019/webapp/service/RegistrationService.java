/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
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

    public void create(Long eventId) throws Exception;

    public Registration find(Long id);

    public void delete(Long id);

    public Registration findByEventAndUser(Event event, User user);
    
    public List<Registration> findByEvent(Event event);

    public List<Registration> findByUser(Long id);
}
