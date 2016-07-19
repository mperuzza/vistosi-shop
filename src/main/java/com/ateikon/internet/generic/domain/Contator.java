/*
 * Contator.java
 *
 * Created on 20 aprile 2006, 9.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ateikon.internet.generic.domain;

/**
 *
 * @author Emiliano
 */
public class Contator extends BaseTableBean{
    
    private String prname;
    
    private String annoco;
    
    private Long nprogr;
    
    
    /** Creates a new instance of Contator */
    public Contator() {
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public String getAnnoco() {
        return annoco;
    }

    public void setAnnoco(String annoco) {
        this.annoco = annoco;
    }

    public Long getNprogr() {
        return nprogr;
    }

    public void setNprogr(Long nprogr) {
        this.nprogr = nprogr;
    }
    
    
    
}
