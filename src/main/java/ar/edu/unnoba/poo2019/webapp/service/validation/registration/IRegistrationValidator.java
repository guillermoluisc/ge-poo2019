/*

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.registration;

import ar.edu.unnoba.poo2019.webapp.model.Registration;

/**
 *
 * @author jpgm
 */
public interface IRegistrationValidator {
    public void validate(Registration registration) throws Exception;
}
