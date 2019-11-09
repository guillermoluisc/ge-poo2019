/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
     @GetMapping
    public String index(Model model){
        List<Event> events = eventService.events();
        model.addAttribute("events", events);
        return "events/index";
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
    
    //@GetMapping("/{id}/edit")
    
    //(pregunta)    la parte de autenticacion va?
    
   /** public String edit(@PathVariable Long id, Model model, Authentication authentication){
       / Event sessionUser = (User)authentication.getPrincipal();
        sessionUser.getEmail();
        Event user = userService.find(id);
        model.addAttribute("user", user);
        return "users/edit";
         }
         */

   
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,@ModelAttribute Event event){
        eventService.update(id,event);
        return "redirect:/events";
    }
    
    
    
    
}