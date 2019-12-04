/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2019.webapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *Payment​:
id: Long
registration: Registration
card: String
cardNumber: String

 * @author guillermo
 */
@Entity
@Table(name="payments")
public class Payment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User owner;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
    
    @Column
    private String cardName;
    
    @Column(name="card_number")
    private String cardNumber;

    
    
    public Payment() {
    }

    public Payment(long id, User user, Event event, String cardName, String cardNumber) {
        this.id = id;
       this.event=event;
       this.owner=user;
        this.cardName = this.cardName;
        this.cardNumber = cardNumber;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String card) {
        this.cardName = card;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
        
}
