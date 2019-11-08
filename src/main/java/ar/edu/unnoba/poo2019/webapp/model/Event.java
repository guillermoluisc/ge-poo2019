/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author guillermo
 */
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private User owner;
    
    @Column(name="event_date")
    private Date eventDate;
    
    @Column(name="start_registration")
    private Date startRegistration;
    
    @Column(name="end_registration")
    private Date endRegistration;
    
    @Column
    private int capacity;
    
    @Column
    private float cost;
    
    @Column(name="private_event")
    private boolean privateEvent;

    
    
    
    public Event(long id, User owner, Date eventDate, Date startRegistrations, Date endRegistrations, int capacity, float cost, boolean privateEvent) {
        this.id = id;
        this.owner = owner;
        this.eventDate = eventDate;
        this.startRegistration = startRegistrations;
        this.endRegistration = endRegistrations;
        this.capacity = capacity;
        this.cost = cost;
        this.privateEvent = privateEvent;
    }

    public Event (){
    }
    
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getStartRegistrations() {
        return startRegistration;
    }

    public void setStartRegistrations(Date startRegistrations) {
        this.startRegistration = startRegistrations;
    }

    public Date getEndRegistrations() {
        return endRegistration;
    }

    public void setEndRegistrations(Date endRegistrations) {
        this.endRegistration = endRegistrations;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isPrivateEvent() {
        return privateEvent;
    }

    public void setPrivateEvent(boolean privateEvent) {
        this.privateEvent = privateEvent;
    }
   
    
    
    
}
