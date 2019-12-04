/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import ar.edu.unnoba.poo2019.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author guillermo
 */
 @Controller
@RequestMapping("/invites")
public class InviteController {
     @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;
     @Autowired
    private SessionService sessionService;
     
     
      @GetMapping("{eventId,userID")
    public String inviteNew(Model model,@PathVariable Long id){
        Invite i =new Invite();
        i.setEvent(eventService.find(id));
        i.setUser(userService.find(id));
        
        model.addAttribute("event",eventService.find(id));
        model.addAttribute("user",userService.find(id));
        
        model.addAttribute("invite", new Invite());
        return "invites/new";
    }
     
     
     
}
