/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.invite;

import ar.edu.unnoba.poo2019.webapp.model.Invite;

/**
 *
 * @author root
 */
public interface IInviteValidator {

    public void validate(Invite invite) throws Exception;

}
