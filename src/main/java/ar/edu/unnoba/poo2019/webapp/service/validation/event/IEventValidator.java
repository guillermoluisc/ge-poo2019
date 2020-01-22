/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.event;

import ar.edu.unnoba.poo2019.webapp.model.Event;

/**
 *
 * @author root
 */
public interface IEventValidator {

    public void validate(Event event) throws Exception;

}
