/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.invite;

import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component(value = "SimpleInviteValidator")
public class InviteValidator implements IInviteValidator {

    @Autowired
    InviteService inviteService;

    @Override
    public void validate(Invite invite) throws Exception {
        if (invite.getUser() == null) {
            throw new Exception("La invitacion no posee usuario");
        }
        if (invite.getEvent() == null) {
            throw new Exception("La invitacion no posee evento");
        }
        if (inviteService.findByUserAndEvent(invite.getUser(), invite.getEvent()) != null) {
            throw new Exception("Ya existe la invitación.");
        }
    }

}
