/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.repository.EventRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class EventServiceImp implements EventService{
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Override
    public List<Event> events() {
        return eventRepository.findAll();
    }

    @Override
    public Event create(Event event) {
        event.setOwner(sessionService.getCurrentUser()); // Guardo el usuario actual.
        return eventRepository.save(event);
    }

    @Override
    public Event find(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event update(Long id, Event event) {
        Event e = eventRepository.findById(id).get();
        e.setEventName(event.getEventName());
        e.setCapacity(event.getCapacity());
        e.setCost(event.getCost());
        e.setStartRegistrations(event.getStartRegistrations());
        e.setEndRegistrations(event.getEndRegistrations());
        e.setEventDate(event.getEventDate());
        e.setPrivateEvent(event.isPrivateEvent());
        e.setLugar(event.getLugar());
        return eventRepository.save(e);  
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);    
    }

    @Override
    public List<Event> findEventsByOwnerId(long ownerId) {
        return eventRepository.findEventsByOwnerId(ownerId);
    }
    
}
