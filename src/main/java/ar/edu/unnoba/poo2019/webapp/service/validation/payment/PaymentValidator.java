/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.payment;

import ar.edu.unnoba.poo2019.webapp.model.Payment;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component(value = "SimplePaymentValidator")
public class PaymentValidator implements IPaymentValidator {

    @Override
    public void validate(Payment payment) throws Exception {
        if (payment.getEvent() == null) {
            throw new Exception("El pago debe tener un evento asociado");
        }
        if (payment.getOwner() == null) {
            throw new Exception("El pago debe tener un propietario asociado");
        }
        if (payment.getCardName().isEmpty()) {
            throw new Exception("Debe introducir el nombre de la tarjeta");
        }
        if (payment.getCardNumber().isEmpty()) {
            throw new Exception("Debe introducir el numero de la tarjeta");
        }
    }

}
