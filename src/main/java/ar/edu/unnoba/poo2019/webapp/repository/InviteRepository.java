/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.repository;

import ar.edu.unnoba.poo2019.webapp.model.Event;
import ar.edu.unnoba.poo2019.webapp.model.Invite;
import ar.edu.unnoba.poo2019.webapp.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    public Invite findByUserAndEvent(User user, Event event);

    public List<Invite> findByUser(User user);

    public List<Invite> findByEvent(Event event);

    //public void deleteInvitesByEventId(Long eventId);
    
}
