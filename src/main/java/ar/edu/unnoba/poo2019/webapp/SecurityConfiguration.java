/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author guillermo
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
    
@Autowired
private UserDetailService userDetailService;



@Override
protected void configure(AutenticationManagerBuilder auth) throws Exeption {
    auth.userDetailService(userDetailService);
    
}

@Override
protected void configure(HttpSecurity http) throw Exeption {
    http.authorizeRequest()
            .antMatchers("/","/login","/logout").permitAll()
            .and().form.Login();
    http.authorizeRequest()
            .antMatchers("/*?")
            .acces("hasRole('ROLE_USER')");
  
 
    
}
@Bean
public PasswordEncore getPasswordEncoder(){
    /**encriptado para que no se guarde plana*/
    return new BCrypPasswordEncore();
}
}