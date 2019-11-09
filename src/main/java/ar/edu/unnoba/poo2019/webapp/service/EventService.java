/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import java.util.List;

/**
 *
 * @author root
 */
public interface EventService {
    
    public List<Event> events();
    
    public Event create(Event event);
    
    public Event find(Long id);
    
    public Event update(Long id,Event event);

    public void delete(Long id);
    
}
