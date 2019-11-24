/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.registration;

import ar.edu.unnoba.poo2019.webapp.model.Payment;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author jpgm
 */
@Component
@Primary
public class RegistrationPaymentValidator extends ValidationDecorator{
    
    @Autowired
    private PaymentService paymentService;

    public RegistrationPaymentValidator(
            @Qualifier(value = "inviteValidator")
            IRegistrationValidator delegate) {
        super(delegate);
    }
    
    @Override
    public void validate(Registration registration) throws Exception{
        if(registration.getEvent().getCost()>0){
            Payment payment = 
                    paymentService
                            .findByEventAndUser(registration.getEvent(),
                                    registration.getUser());
            if (payment == null) throw new Exception("Pago sin realizar");
        }
        //valida que exista un pago registrado antes de aprobar la inscripcion
        super.validate(registration);
    }
    
}
