/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Payment;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public interface PaymentService {
    
    public List<Payment> users();
    
    public Payment create(Payment payment);
    
    public Payment find(Long id);
    
    public Payment update(Long id,Payment payment);

    public void delete(Long id);
    
}
