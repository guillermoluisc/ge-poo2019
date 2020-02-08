/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.InviteService;
import ar.edu.unnoba.poo2019.webapp.service.RegistrationService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author root
 */
@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private InviteService inviteService;

    @GetMapping("/myRegistrations")
    public String registrate(Model model) {
        Long id = sessionService.getCurrentUser().getId();
        List<Registration> registrations = registrationService.findByUser(id);
        model.addAttribute("registrations", registrations);
        model.addAttribute("currentUser", sessionService.getCurrentUser());
        return "registrations/myRegistrations";
    }

    @GetMapping("/{id}/registrate")
    public String registrate(@PathVariable Long id, Model model) {
        Event e = eventService.find(id);
        model.addAttribute("event", e);
        return "registrations/registrate";
    }

    @GetMapping("/{id}/confirmRegistration")

    public String confirmRegistration(@PathVariable Long id, Model model) throws Exception {
        try {

            Event e = eventService.find(id);

            if (e.isPrivateEvent()) {
                Invite invite = inviteService.findByUserAndEvent(sessionService.getCurrentUser(), e);

                if (invite == null) {
                    throw new Exception("Sin invitacion");
                }
            }

            if (e.getCost() > 0) {
                return "redirect:/payments/{id}";
            } else {
                registrationService.create(id);
                return "registrations/confirmedRegistration";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/error/error";
        }
    }

    /**
     * @PostMapping public String create(@PathVariable Long id) throws
     * Exception{ User user = sessionService.getCurrentUser();
     * registrationService.create(id, user); return null; }*
     */
}
