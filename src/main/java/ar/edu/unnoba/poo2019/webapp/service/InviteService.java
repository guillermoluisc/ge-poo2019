/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.service;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public interface InviteService {

    public List<Invite> invites();

    public Invite create(Invite invite) throws Exception;

    public Invite find(Long id);

    public Invite update(Long id, Invite invite);

    public void delete(Long id);

    public Invite findByUserAndEvent(User user, Event event);
    
    public List<Invite> findByEvent(Event event);

    public List<Invite> findByUser(User user);

    public void deleteInvitesByEventId(Long id);
}
