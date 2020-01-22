/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.payment;

import ar.edu.unnoba.poo2019.webapp.model.Payment;

/**
 *
 * @author root
 */
public interface IPaymentValidator {

    public void validate(Payment payment) throws Exception;

}
