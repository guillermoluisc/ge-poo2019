/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;

/**
 *
 * @author jpgm
 */
public interface UserService {
    
    public List<User> users();
    
    public User create(User user);
    
    public User find(Long id);
    
    public User update(Long id,User user);
}
