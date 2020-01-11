/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.User;
import ar.edu.unnoba.poo2019.webapp.repository.InviteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class InviteServiceImp implements InviteService {

    @Autowired
    private InviteRepository inviteRepository;
    
    @Override
    public List<Invite> invites() {
        return inviteRepository.findAll();
    }

    @Override
    public Invite create(Invite invite) {
        return inviteRepository.save(invite);
    }

    @Override
    public Invite find(Long id) {
        return inviteRepository.findById(id).get();
    }

    @Override
    public Invite update(Long id, Invite invite) {
        Invite i=inviteRepository.findById(id).get();
        i.setEvent(invite.getEvent());
        i.setUser(invite.getUser());
        return inviteRepository.save(i);
    }

    @Override
    public void delete(Long id) {
    inviteRepository.deleteById(id);
    
    }

    @Override
    public Invite findByUserAndEvent(User user, Event event) {
        return inviteRepository.findByUserAndEvent(user, event);
        
    }
    
}
