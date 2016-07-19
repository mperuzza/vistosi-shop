/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.web;

import java.util.List;

/**
 *
 * @author emiliano
 */
public class FormSendWishlist {
    
    String destinatari;
    
    String sender;
    
    String note;
    
    Long wishlistId;
    
    boolean sendSelf = false;

    public String getDestinatari() {
        return destinatari;
    }

    public void setDestinatari(String destinatari) {
        this.destinatari = destinatari;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isSendSelf() {
        return sendSelf;
    }

    public void setSendSelf(boolean sendSelf) {
        this.sendSelf = sendSelf;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }
    
    List<String> toList;

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    
    
}
