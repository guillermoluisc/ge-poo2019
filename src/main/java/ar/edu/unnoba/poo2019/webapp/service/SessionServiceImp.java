/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class SessionServiceImp implements SessionService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder
            .getContext()
            .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User u = (User) this.userRepository.findByEmail(userDetail.getUsername()).get(0);
        return u;
    }
    
}
