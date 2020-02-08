/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.repository.EventRepository;
import ar.edu.unnoba.poo2019.webapp.repository.InviteRepository;
import ar.edu.unnoba.poo2019.webapp.service.validation.event.EventValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class EventServiceImp implements EventService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private InviteService inviteService;

    @Autowired
    private EventValidator eventValidator;

    @Override
    public List<Event> events() {
        return eventRepository.findAllEvents();
    }

    @Override
    public Event create(Event event) throws Exception {
        event.setOwner(sessionService.getCurrentUser()); // Guardo el usuario actual.
        eventValidator.validate(event);
        return eventRepository.save(event);
    }

    @Override
    public Event find(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event update(Long id, Event event) throws Exception {
        Event oldEvent = this.find(id);
        if (event.getCapacity() >= oldEvent.cantidadRegistrations()) {     // Controlo que la capacidad no sea menor a los inscriptos
            if (oldEvent.hasRegistrations() && oldEvent.getCost() != event.getCost()) {   // Controlo que no se pueda cambiar el precio si hay registraciones
                throw new Exception("No se puede cambiar el costo si ya hay inscriptos");
            } else {
                oldEvent.setEventName(event.getEventName());    // Guardo el evento
                oldEvent.setCapacity(event.getCapacity());
                oldEvent.setCost(event.getCost());
                oldEvent.setStartRegistrations(event.getStartRegistrations());
                oldEvent.setEndRegistrations(event.getEndRegistrations());
                oldEvent.setEventDate(event.getEventDate());
                oldEvent.setPrivateEvent(event.isPrivateEvent());
                oldEvent.setLugar(event.getLugar());
                eventValidator.validate(oldEvent);
                return eventRepository.save(oldEvent);
            }
        } else {
            throw new Exception("La capacidad no puede ser menor que la cantidad de inscriptos");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Event event = this.find(id);
        if (!event.hasRegistrations()) {     // Controlo que no halla inscriptos
            inviteService.deleteInvitesByEventId(id);
            eventRepository.deleteById(id);
        } else {
            throw new Exception("No se puede eliminar el evento porque posee inscripciones");
        }
    }

    @Override
    public List<Event> findEventsByOwnerId(long ownerId) {
        return eventRepository.findEventsByOwnerId(ownerId);
    }

}
