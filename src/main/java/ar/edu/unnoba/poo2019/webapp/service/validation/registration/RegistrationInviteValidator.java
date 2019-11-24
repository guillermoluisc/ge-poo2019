/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.registration;

import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jpgm
 */
@Component(value = "inviteValidator")
public class RegistrationInviteValidator extends ValidationDecorator{
    
    @Autowired
    private InviteService inviteService;
    
    public RegistrationInviteValidator(
            @Qualifier("SimpleRegistrationValidator") 
            IRegistrationValidator delegate) {
        super(delegate);
    }

    @Override
    public void validate(Registration registration) throws Exception{
        //valida que existe una invitacion para el usuario a este evento
        if(registration.getEvent().isPrivateEvent()){
            Invite invite = inviteService.findByUserAndEvent(
                    registration.getUser(),registration.getEvent());
            if (invite == null) throw new Exception("Sin invitacion");
        }
        super.validate(registration);
    }
    
}
    