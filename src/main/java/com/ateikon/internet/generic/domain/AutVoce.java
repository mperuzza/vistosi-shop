/*
 * AutVoce.java
 *
 * Created on 5 agosto 2004, 12.12
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;


/**
 * @author Emiliano
 */
public class AutVoce extends BaseTableBean implements Serializable {
    
    /**
     * Holds value of property cdvoce.
     */
    private String cdvoce;
    
    /**
     * Holds value of property cdutente.
     */
    private String cdutente;
    
    /**
     * Holds value of property fgabilitato.
     */
    private String fgabilitato;
    
    /**
     * Holds value of property profil_inse.
     */
    private String profil_inse;
    
    public AutVoce() {
    }
    
    /**
     * Getter for property cdvoce.
     * @return Value of property cdvoce.
     */
    public String getCdvoce() {
        return this.cdvoce;
    }    
    
    /**
     * Setter for property cdvoce.
     * @param cdvoce New value of property cdvoce.
     */
    public void setCdvoce(String cdvoce) {
        this.cdvoce = cdvoce;
    }
    
    /**
     * Getter for property cdutente.
     * @return Value of property cdutente.
     */
    public String getCdutente() {
        return this.cdutente;
    }
    
    /**
     * Setter for property cdutente.
     * @param cdutente New value of property cdutente.
     */
    public void setCdutente(String cdutente) {
        this.cdutente = cdutente;
    }
    
    /**
     * Getter for property fgabilitato.
     * @return Value of property fgabilitato.
     */
    public String getFgabilitato() {
        return this.fgabilitato;
    }
    
    /**
     * Setter for property fgabilitato.
     * @param fgabilitato New value of property fgabilitato.
     */
    public void setFgabilitato(String fgabilitato) {
        this.fgabilitato = fgabilitato;
    }
    
    /**
     * Getter for property profil_inse.
     * @return Value of property profil_inse.
     */
    public String getProfil_inse() {
        return this.profil_inse;
    }
    
    /**
     * Setter for property profil_inse.
     * @param profil_inse New value of property profil_inse.
     */
    public void setProfil_inse(String profil_inse) {
        this.profil_inse = profil_inse;
    }
    
}
