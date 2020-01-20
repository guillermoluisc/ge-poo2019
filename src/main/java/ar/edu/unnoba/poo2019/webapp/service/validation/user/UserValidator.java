/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service.validation.user;

import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.service.UserService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component(value = "SimpleUserValidator")
public class UserValidator implements IUserValidator {

    @Autowired
    private UserService userService;
    
    @Override
    public void validate(User user) throws Exception {
        if (!isValid(user.getEmail())) {
            throw new Exception("Email no válido");
        }
        if (userService.existe(user.getEmail()) == true) {
            throw new Exception("El usuario ya existe");
        }
        if (user.getPassword().isEmpty()) {
            throw new Exception("Debe introducir una contraseña");
        }
        if (user.getFirstName().isEmpty()) {
            throw new Exception("Debe introducir un nombre");
        }
        if (user.getLastName().isEmpty()) {
            throw new Exception("Debe introducir un apellido");
        }
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
}
