/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/events") /** preguntar si esto lleva el /user tambien?*/

public class EventController {
    @Autowired
    private EventService eventService;
    
    @Autowired
    private SessionService sessionService;
    
    @GetMapping
    public String index(Model model){
        List<Event> events = eventService.events();
        model.addAttribute("events", events);
        return "events/index";
    }
    
    @GetMapping("/myEvents")
    public String myEvents(Model model){
        List<Event> events = eventService.findEventsByOwnerId(sessionService.getCurrentUser().getId());
        model.addAttribute("events", events);
        return "events/myEvents";
    }
    
    @GetMapping("/new")
    public String eventNew(Model model){
        model.addAttribute("event", new Event());
        return "events/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute Event event){
        eventService.create(event);
        return "redirect:/events";
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable ("id") Long id){
        eventService.delete(id);
        return "redirect:/events";
    }
    
    @GetMapping("/{id}/edit")     //(pregunta)    la parte de autenticacion va?
    public String edit(@PathVariable Long id, Model model){
        Event event = eventService.find(id);
        model.addAttribute("event", event);
        return "events/edit";
    }
    
   
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,@ModelAttribute Event event){
        eventService.update(id,event);
        return "redirect:/events";
    }
    
    @GetMapping("/{id}/registrate")
    public String registrate(@PathVariable Long id, Model model){
        Event e = eventService.find(id);
        model.addAttribute("event", e);
        return "events/registrate";
    }
    
    // Ver si se puede poner en AppConfiguration o si se puede hacer otra cosa
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
}