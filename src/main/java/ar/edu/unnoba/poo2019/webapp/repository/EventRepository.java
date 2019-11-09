/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.repository;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author root
 */
public interface EventRepository extends JpaRepository<Event, Long>{
    
    //public List<Event> findById(String email);
}
