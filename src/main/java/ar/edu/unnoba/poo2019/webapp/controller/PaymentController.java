/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.controller;

import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private PaymentService paymentService;
    
   /* @GetMapping
    public String index(Model model){
        List<User> users = userService.users();
        model.addAttribute("users", users);
        return "users/index";
    }*/
    
    @GetMapping("/new")
    public String paymentNew(@ModelAttribute Payment payment, Model model){
        System.out.println("GetMapping /new paymentController");
        model.addAttribute("payment", payment);
        return "payments/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute Payment payment) throws Exception{     // en vez de pasar un payment pasar event id y el user obtenerlo aca.
        System.out.println("create paymentController " + payment);
        paymentService.create(payment.getEvent().getId(),payment.getOwner());
        return "redirect:/registrations/confirmedRegistrations";
    }
    
    /*@GetMapping("/{id}/delete")
    public String delete(@PathVariable ("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model, Authentication authentication){
        User sessionUser = (User)authentication.getPrincipal();
        sessionUser.getEmail();
        User user = userService.find(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,@ModelAttribute User user){
        userService.update(id,user);
        return "redirect:/users";
    }*/
    
}

    

