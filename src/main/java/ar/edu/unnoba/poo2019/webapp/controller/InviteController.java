/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.InviteService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
import ar.edu.unnoba.poo2019.webapp.service.UserService;
import java.util.List;
import java.util.Objects;
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
    private InviteService inviteService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{eventId}/{userId}/sendInv")
    public String sendInv(Model model, @PathVariable Long eventId, @PathVariable Long userId) throws Exception {
        Event event = eventService.find(eventId);
        if (Objects.equals(event.getOwner().getId(), sessionService.getCurrentUser().getId())) {
            Invite i = new Invite();
            i.setEvent(event);
            i.setUser(userService.find(userId));
            inviteService.create(i);
            return "redirect:/invites/{eventId}/invite";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    @GetMapping("/{eventId}/invite")
    public String invite(Model model, @PathVariable Long eventId) throws Exception {
        if (Objects.equals(eventService.find(eventId).getOwner().getId(), sessionService.getCurrentUser().getId())) {
            List<User> users = userService.users();
            model.addAttribute("users", users);
            model.addAttribute("eventId", eventId);
            return "invites/inviteUsers";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

    @GetMapping("/myInvites")
    public String invite(Model model) {
        User user = sessionService.getCurrentUser();
        List<Invite> invites = inviteService.findByUser(user);
        model.addAttribute("invites", invites);
        return "invites/myInvites";
    }

    @GetMapping("/{inviteId}/delete")
    public String delete(@PathVariable Long inviteId) throws Exception {
        Invite inv = inviteService.find(inviteId);
        if (Objects.equals(sessionService.getCurrentUser().getId(), inv.getUser().getId())) {    // Controlo que sea el propio usuario 
            inviteService.delete(inviteId);
            return "redirect:/invites/myInvites";
        }
        throw new Exception("Permiso denegado usuario invalido");
    }

}
