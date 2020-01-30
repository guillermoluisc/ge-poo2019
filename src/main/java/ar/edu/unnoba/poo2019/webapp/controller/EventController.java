/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*1*/
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.InviteService;
import ar.edu.unnoba.poo2019.webapp.service.RegistrationService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author root
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private InviteService inviteService;

    @GetMapping
    public String index(Model model) {
        List<Event> events = eventService.events();
        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("/myEvents")
    public String myEvents(Model model) {
        List<Event> events = eventService.findEventsByOwnerId(sessionService.getCurrentUser().getId());
        model.addAttribute("events", events);
        return "events/myEvents";
    }

    @GetMapping("/new")
    public String eventNew(Model model) {
        model.addAttribute("event", new Event());
        return "events/new";
    }

    @PostMapping
    public String create(@ModelAttribute Event event) throws Exception {
        eventService.create(event);
        return "redirect:/events/myEvents";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) throws Exception {
        Event event = eventService.find(id);
        if (Objects.equals(sessionService.getCurrentUser().getId(), event.getOwner().getId())) {    // Controlo que sea el propio usuario 
            eventService.delete(id);
            return "redirect:/events/myEvents";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        Event event = eventService.find(id);
        if (Objects.equals(sessionService.getCurrentUser().getId(), event.getOwner().getId())) {  // Controlo que sea el propio usuario 
            model.addAttribute("event", event);
            return "events/edit";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Event event) throws Exception {
        Event oldEvent = eventService.find(id);
        if (Objects.equals(sessionService.getCurrentUser().getId(), oldEvent.getOwner().getId())) {    // Controlo que sea el propio usuario 
            eventService.update(id, event);
            return "redirect:/events/myEvents";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    @GetMapping("/{id}/eventDetails")
    public String detail(@PathVariable Long id, Model model) throws Exception {
        Event event = eventService.find(id);
        if (Objects.equals(sessionService.getCurrentUser().getId(), event.getOwner().getId())) {  // Controlo que sea el propio usuario 
            List<Registration> registrations = event.getRegistrations();
            System.out.println(registrations.get(0).getUser().getFirstName());
            List<Invite> invites = inviteService.findByEvent(event);
            model.addAttribute("event", event);
            model.addAttribute("registrations", registrations);
            model.addAttribute("invites", invites);
            return "events/eventDetails";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    // Ver si se puede poner en AppConfiguration o si se puede hacer otra cosa
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
