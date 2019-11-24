/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.repository.PaymentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public List<Payment> users() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);     
    }

    @Override
    public Payment find(Long id) {
       return paymentRepository.findById(id).get();
    }

    @Override
    public Payment update(Long id, Payment payment) {
        Payment p=paymentRepository.findById(id).get();
        p.setCard(payment.getCard());
        p.setCardNumber((payment.getCardNumber()));
        p.setRegistration(payment.getRegistration());
        return paymentRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
    
}
