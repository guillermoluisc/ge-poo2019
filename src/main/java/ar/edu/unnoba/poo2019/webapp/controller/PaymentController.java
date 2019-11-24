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
@RequestMapping("/payment")
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
    public String paymentNew(Model model){
        model.addAttribute("payment", new Payment());
        return "payment/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute Payment payment) throws Exception{
        paymentService.create(payment.getEvent().getId(),payment.getOwner());
        return "redirect:/users";
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

    
