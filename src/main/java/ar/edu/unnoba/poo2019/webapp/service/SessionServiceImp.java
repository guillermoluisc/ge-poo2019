/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class SessionServiceImp implements SessionService{

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder
            .getContext()
            .getAuthentication();
        User u = (User) auth.getPrincipal();
        return u;
    }
     
}
