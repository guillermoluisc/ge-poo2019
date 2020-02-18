/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.EventService;
import ar.edu.unnoba.poo2019.webapp.service.PaymentService;
import ar.edu.unnoba.poo2019.webapp.service.SessionService;
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
 * @author guillermo
 */
@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PaymentService paymentService;


    
    /*Nuevo pago, agrega al modelo un eventom y un pago*/
    @GetMapping("/{id}")
    public String paymentNew(Model model, @PathVariable Long id) {
        model.addAttribute("event", eventService.find(id));
        model.addAttribute("payment", new Payment());
        return "payments/new";
    }
/*Crea el pago y verifica los datos de regiistracion*/
    @PostMapping("/{eventId}")
    public String create(Model model, @PathVariable Long eventId, @ModelAttribute("payment") Payment payment) throws Exception {     // en vez de pasar un payment pasar event id y el user obtenerlo aca.
        try {
            User u = sessionService.getCurrentUser();
            Event e = eventService.find(eventId);
            payment.setOwner(u);
            payment.setEvent(e);
            paymentService.create(payment);
            return "registrations/confirmedRegistration";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/error/error";
        }
    }

 
}
