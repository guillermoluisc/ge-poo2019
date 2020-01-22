/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpgm
 */
@Service
public interface UserService {
    
    public List<User> users();
    
    public User create(User user) throws Exception;
    
    public User find(Long id);
    
    public User update(Long id,User user);

    public void delete(Long id);
    
    public List<User> findByEmail(String email);
    
    public boolean existe (String email);
    
}
