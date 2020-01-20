/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public interface EventService {
    
    public List<Event> events();
    
    public List<Event> findEventsByOwnerId(long ownerId);
    
    public Event create(Event event) throws Exception;
    
    public Event find(Long id);
    
    public Event update(Long id,Event event) throws Exception;

    public void delete(Long id) throws Exception;
    
}
