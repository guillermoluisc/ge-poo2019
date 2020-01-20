/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.user;

import ar.edu.unnoba.poo2019.webapp.model.User;

/**
 *
 * @author root
 */
public interface IUserValidator {

    public void validate(User user) throws Exception;

}
