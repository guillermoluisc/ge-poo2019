/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public interface SessionService {
    
    public User getCurrentUser(); // Devuelve el usuario de la sesion.
    
}
