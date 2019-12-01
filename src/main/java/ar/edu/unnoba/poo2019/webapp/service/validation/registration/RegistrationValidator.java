/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.registration;

import ar.edu.unnoba.poo2019.webapp.model.Registration;
import ar.edu.unnoba.poo2019.webapp.service.RegistrationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jpgm
 */

@Component(value = "SimpleRegistrationValidator")
public class RegistrationValidator implements IRegistrationValidator{

    @Autowired
    private RegistrationService registrationService;
    
    @Override
    public void validate(Registration registration) throws Exception{
        //valida que el evento tenga cupo, que este dentro de la fecha de inscripcion, y que no este ya inscripto al evento.
        Date today = new Date();
        if(today.compareTo(registration.getEvent().getStartRegistrations())<0
                || today.compareTo(registration.getEvent().getEndRegistrations())>0){
            throw new Exception("La fecha para inscribirse no esta correcta");
        }
        if(registration.getEvent().getAvailability() < 1){
            throw new Exception("No hay cupo");
        }
        if(registrationService.findByEventAndUser(registration.getEvent(), registration.getUser()) != null){
            throw new Exception("El usuario ya se inscribio al evento");
        }
    }
    
}
