/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.RegistrationService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import ar.edu.unnoba.poo2019.webapp.service.UserService;
import ar.edu.unnoba.poo2019.webapp.service.validation.registration.IRegistrationValidator;
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
@RequestMapping("/registrations")
public class RegistrationController {
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private IRegistrationValidator registrationValidator;
    
    @Autowired
    private EventService eventService;
    
    @GetMapping("/{id}/registrate") 
    public String registrate(@PathVariable Long id, Model model){ // Ver si pasar el id o el evento desde la pagina de eventos
        Event e = eventService.find(id);
        model.addAttribute("event", e);
        return "registrations/registrate";
    }
    
    @GetMapping("/{id}/confirmRegistration")
    public String confirmRegistration(@PathVariable Long id, Model model) throws Exception{
        Event e = eventService.find(id);
        User u = sessionService.getCurrentUser();
        if(e.getCost() > 0){
            Payment pay = new Payment();
            pay.setEvent(e);
            pay.setOwner(u);
            model.addAttribute("payment", pay);
            return "redirect:/payments/new";
        }else{ 
            registrationService.create(id, u);
            return "registrations/confirmedRegistration";
        }
        
    }
    
    /**@PostMapping
    public String create(@PathVariable Long id) throws Exception{
        User user = sessionService.getCurrentUser();
        registrationService.create(id, user);
        return null;
    }**/
    
}
