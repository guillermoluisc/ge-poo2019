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
public abstract class ValidationDecorator implements IRegistrationValidator{
    
    private IRegistrationValidator delegate;
    
    public ValidationDecorator(IRegistrationValidator delegate){
        this.delegate = delegate;
    }

    @Override
    public void validate(Registration registration) throws Exception{
        delegate.validate(registration);
    }
    
    
}
