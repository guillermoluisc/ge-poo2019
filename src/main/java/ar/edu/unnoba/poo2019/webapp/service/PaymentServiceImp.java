/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.repository.PaymentRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author root
 */
@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private EventService eventService;
    
    @Override
    public List<Payment> users() {
        return paymentRepository.findAll();
    }

    

    @Override
    public Payment find(Long id) {
       return paymentRepository.findById(id).get();
    }

    @Override
    public Payment update(Long id, Payment payment) {
        Payment p=paymentRepository.findById(id).get();
        p.setCardName(payment.getCardName());
        p.setCardNumber((payment.getCardNumber()));
        p.setOwner(payment.getOwner());
        p.setEvent(payment.getEvent());
        return paymentRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
    @Override
    public Payment findByEventAndUser(Event event, User user) {
        List<Payment> payments = paymentRepository.findByEventAndUser(event,user);
        if(payments.isEmpty())
            return null;
        else
            return payments.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Payment create(Long eventId, User user) throws Exception{
        Event event = eventService.find(eventId);
        Payment p = new Payment();
        if(event.getCost() > 0 &&
                this.findByEventAndUser(event, user)==null){
            p.setEvent(event);
            p.setOwner(user);
            p = paymentRepository.save(p);
            registrationService.create(p.getEvent().getId(),p.getOwner());   
            return p;
        }else{
            throw new Exception("Error");
        }
        
    }

 

    
}
