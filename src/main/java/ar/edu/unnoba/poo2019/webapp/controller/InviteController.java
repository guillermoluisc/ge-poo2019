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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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
    /*Envio de invitaciones
    Controla si el usuario es el correcto*/
    @GetMapping("/{eventId}/{userId}/sendInv")
    public String sendInv(Model model, @PathVariable Long eventId, @PathVariable Long userId) throws Exception {
        try {
            Event event = eventService.find(eventId);
            if (Objects.equals(event.getOwner().getId(), sessionService.getCurrentUser().getId())) {
                Invite i = new Invite();
                i.setEvent(event);
                i.setUser(userService.find(userId));
                inviteService.create(i);
                return "redirect:/invites/{eventId}/invite";
            }
            throw new Exception("Permiso denegado usuario invalido");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/error/error";
        }
    }
/*Envio de invitaciones
    Controla si el usuario es el correcto
    Agrega al modelo una lista de usuarios para poder verlos e invitarlos
    un usuario
    un evento*/
    @GetMapping("/{eventId}/invite")
    public String invite(Model model, @PathVariable Long eventId) throws Exception {
        try {
            if (Objects.equals(eventService.find(eventId).getOwner().getId(), sessionService.getCurrentUser().getId())) {
                List<User> users = userService.users();
                model.addAttribute("users", users);
                model.addAttribute("eventId", eventId);
                return "invites/inviteUsers";
            }
            throw new Exception("Permiso denegado usuario invalido");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/error/error";
        }
    }
/*Agrega al modelo una lista de invitaciones un usuario(actual)
    para poder ver las invitaciones recividas*/
    @GetMapping("/myInvites")
    public String invite(Model model) {
        User user = sessionService.getCurrentUser();
        List<Invite> invites = inviteService.findByUser(user);
        model.addAttribute("invites", invites);
        model.addAttribute("currentUser", sessionService.getCurrentUser());
        return "invites/myInvites";
    }
/*Verifical si el usuario es el correcto, ademas de eliminar la invitacion en caso de serlo*/
    @GetMapping("/{inviteId}/delete")
    public String delete(Model model, @PathVariable Long inviteId, HttpServletRequest request) throws Exception {
        try {
            Invite inv = inviteService.find(inviteId);
            if ((sessionService.getCurrentUser().getId() == inv.getUser().getId()) || (sessionService.getCurrentUser().getId() == inv.getEvent().getOwner().getId())) {    // Controlo que sea el user de invite o el user que la creo (dueño del evento)
                inviteService.delete(inviteId);
                
                String referer = request.getHeader("Referer");
                return "redirect:" + referer;
            }
            throw new Exception("Permiso denegado usuario invalido");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/error/error";
        }
    }

}
