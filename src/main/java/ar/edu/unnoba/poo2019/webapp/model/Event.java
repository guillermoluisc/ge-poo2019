/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author guillermo
 */
@Entity
@Table(name="events")

@NamedQuery(name = "Event.findEventsByOwnerId",
        query = "SELECT e FROM Event e WHERE e.owner.id =: ownerId ORDER BY e.eventDate ASC"
        
)

@NamedQuery(name = "Event.findAllEvents",
        query = "SELECT e FROM Event e ORDER BY e.eventDate ASC"
)

@NamedQuery(name = "Event.findRegistrationsByEventId",  // Esto es para cambiar la coleccion por consulta, preguntar
        query = "SELECT r FROM Registration r WHERE r.event.id =: eventId"
)

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="event_name")
    private String eventName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    
    @Column(name="event_date")
    private Date eventDate;
    
    @Column(name="start_registrations")
    private Date startRegistrations;
    
    @Column(name="end_registrations")
    private Date endRegistrations;
    
    @Column
    private int capacity;
    
    @Column
    private float cost;
    
    @Column(name="private_event")
    private boolean privateEvent;
    
    @Column
    private String lugar;
    
    @OneToMany(mappedBy = "event")
    private List<Registration> registrations = new ArrayList<Registration>();
    
    
    public Event(long id, String eventName, User owner, Date eventDate, Date startRegistrations, Date endRegistrations, int capacity, float cost, boolean privateEvent, String lugar) {
        this.id = id;
        this.eventName = eventName;
        this.owner = owner;
        this.eventDate = eventDate;
        this.startRegistrations = startRegistrations;
        this.endRegistrations = endRegistrations;
        this.capacity = capacity;
        this.cost = cost;
        this.privateEvent = privateEvent;
        this.lugar = lugar;
    }

    public Event (){
    }

    
    
    public boolean isFree(){
        return cost == 0;
    }
    
    public boolean isAvailableQuota(){  // Retorna si hay cupo disponible
        return getAvailability() > 0;
    }
    
    public int getAvailability(){   // Retorna la cantidad de cupos disponibles
        return capacity - getRegistrations().size();
    }
    
    public boolean hasRegistrations(){  // Retorna si el evento tiene registraciones
        return !getRegistrations().isEmpty();
    }
    
    public List<Registration> getRegistrations(){
        return registrations;
    }
    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
        return startRegistrations;
    }

    public void setStartRegistrations(Date startRegistrations) {
        this.startRegistrations = startRegistrations;
    }

    public Date getEndRegistrations() {
        return endRegistrations;
    }

    public void setEndRegistrations(Date endRegistrations) {
        this.endRegistrations = endRegistrations;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int cantidadRegistrations() {
        return getRegistrations().size();
    }
    
}
