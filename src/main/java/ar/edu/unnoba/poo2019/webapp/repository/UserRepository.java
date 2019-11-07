/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.repository;

import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpgm
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    public List<User> findByEmail(String email);
    
}
