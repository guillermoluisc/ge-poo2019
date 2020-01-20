/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.repository.EventRepository;
import ar.edu.unnoba.poo2019.webapp.repository.RegistrationRepository;
import ar.edu.unnoba.poo2019.webapp.service.validation.registration.RegistrationValidator;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author root
 */
@Service
public class RegistrationServiceImp implements RegistrationService{
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
        @Autowired
    private RegistrationValidator validator;
        
    @Autowired
    private SessionService sessionService;

    @Override
    public List<Registration> registrations() {
        return registrationRepository.findAll();
        
    }
    

    @Transactional
    @Override
    public void create(Long eventId) throws Exception{
        User user = sessionService.getCurrentUser();
        Event e = eventRepository.getOne(eventId);
        Registration reg = new Registration();
        reg.setEvent(e);
        reg.setUser(user);
        Date today = new Date();
        reg.setCreatedAt(today);
        validator.validate(reg);
        registrationRepository.save(reg);
    }

    @Override
    public Registration find(Long id) {
        return registrationRepository.findById(id).get();
    }

    @Override
    public Registration update(Long id, Registration registration) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public Registration findByEventAndUser(Event event, User user) {
        List<Registration> registrations = registrationRepository.findByEventAndUser(event,user);
        if(registrations.isEmpty())
            return null;
        else
            return registrations.get(0);
    }

    
}