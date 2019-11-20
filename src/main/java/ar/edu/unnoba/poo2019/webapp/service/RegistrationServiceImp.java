/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.repository.RegistrationRepository;
import java.util.List;
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

    @Override
    public List<Registration> registrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration create(Registration registration) {
        return registrationRepository.save(registration);
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
    
}