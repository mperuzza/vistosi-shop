/*
 * Constants.java
 *
 * Created on 26 luglio 2004, 16.15
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Emiliano
 */
public class AtkConstants extends Object implements Serializable {
    
    /**
     * Holds value of property cdazie.
     */
    private String cdazie;
    
    /**
     * Holds value of property cddipa.
     */
    private String cddipa;
    
    /**
     * Holds value of property profil.
     */
    private String profil;
    
    /**
     * Holds value of property tkmagaTV.
     */
    private int tkmagaTV;
    
    /**
     * Holds value of property tkmagaUD.
     */
    private int tkmagaUD;
    
    /**
     * Holds value of property tkmagaVR.
     */
    private int tkmagaVR;

    /**
     * Holds value of property owner.
     */
    private String owner;
    
    public AtkConstants() {
    }
    
    /**
     * Getter for property cdazie.
     * @return Value of property cdazie.
     */
    public String getCdazie() {
        return this.cdazie;
    }    
    
    /**
     * Setter for property cdazie.
     * @param cdazie New value of property cdazie.
     */
    public void setCdazie(String cdazie) {
        this.cdazie = cdazie;
    }
    
    /**
     * Getter for property cddipa.
     * @return Value of property cddipa.
     */
    public String getCddipa() {
        return this.cddipa;
    }
    
    /**
     * Setter for property cddipa.
     * @param cddipa New value of property cddipa.
     */
    public void setCddipa(String cddipa) {
        this.cddipa = cddipa;
    }
    
    /**
     * Getter for property profil.
     * @return Value of property profil.
     */
    public String getProfil() {
        return this.profil;
    }
    
    /**
     * Setter for property profil.
     * @param profil New value of property profil.
     */
    public void setProfil(String profil) {
        this.profil = profil;
    }
    
    /**
     * Getter for property tkmagaTV.
     * @return Value of property tkmagaTV.
     */
    public int getTkmagaTV() {
        return this.tkmagaTV;
    }
    
    /**
     * Setter for property tkmagaTV.
     * @param tkmagaTV New value of property tkmagaTV.
     */
    public void setTkmagaTV(int tkmagaTV) {
        this.tkmagaTV = tkmagaTV;
    }
    
    /**
     * Getter for property tkmagaUD.
     * @return Value of property tkmagaUD.
     */
    public int getTkmagaUD() {
        return this.tkmagaUD;
    }
    
    /**
     * Setter for property tkmagaUD.
     * @param tkmagaUD New value of property tkmagaUD.
     */
    public void setTkmagaUD(int tkmagaUD) {
        this.tkmagaUD = tkmagaUD;
    }
    
    /**
     * Getter for property tkmagaVR.
     * @return Value of property tkmagaVR.
     */
    public int getTkmagaVR() {
        return this.tkmagaVR;
    }
    
    /**
     * Setter for property tkmagaVR.
     * @param tkmagaVR New value of property tkmagaVR.
     */
    public void setTkmagaVR(int tkmagaVR) {
        this.tkmagaVR = tkmagaVR;
    }

    /**
     * Getter for property owner.
     * @return Value of property owner.
     */
    public String getOwner() {

        return this.owner;
    }

    /**
     * Setter for property owner.
     * @param owner New value of property owner.
     */
    public void setOwner(String owner) {

        this.owner = owner;
    }
    
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);        
    }    
    
}
