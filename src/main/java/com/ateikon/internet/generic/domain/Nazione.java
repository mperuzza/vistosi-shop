/*
 * AutVoce.java
 *
 * Created on 5 agosto 2004, 12.12
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;

import com.ateikon.internet.generic.domain.BaseTableBean;

/**
 * @author Emiliano
 */
public class Nazione extends BaseTableBean implements Serializable {
    
    /**
     * Holds value of property dsnazione.
     */
    private String dsnazione;
    
    /**
     * Holds value of property cdnazione.
     */
    private String cdnazione;
    
    /**
     * Holds value of property cdcont.
     */
    private String cdcont;
    
    /**
     * Holds value of property cdnazione_m.
     */
    private String cdnazione_m;
    
    /**
     * Holds value of property continente.
     */
    //private Continente continente;
    
    public Nazione() {
    }
    
    /**
     * Getter for property cdvoce.
     * @return Value of property cdvoce.
     */
    public String getDsnazione() {
        return this.dsnazione;
    }    
    
    /**
     * Setter for property cdvoce.
     * @param cdvoce New value of property cdvoce.
     */
    public void setDsnazione(String dsnazione) {
        this.dsnazione = dsnazione;
    }
    
    /**
     * Getter for property cdutente.
     * @return Value of property cdutente.
     */
    public String getCdnazione() {
        return this.cdnazione;
    }
    
    /**
     * Setter for property cdutente.
     * @param cdutente New value of property cdutente.
     */
    public void setCdnazione(String cdnazione) {
        this.cdnazione = cdnazione;
    }
    
    /**
     * Getter for property cdcont.
     * @return Value of property cdcont.
     */
    public String getCdcont() {
        return this.cdcont;
    }
    
    /**
     * Setter for property cdcont.
     * @param cdcont New value of property cdcont.
     */
    public void setCdcont(String cdcont) {
        this.cdcont = cdcont;
    }
    
    /**
     * Getter for property cdnazione_m.
     * @return Value of property cdnazione_m.
     */
    public String getCdnazione_m() {
        return this.cdnazione_m;
    }
    
    /**
     * Setter for property cdnazione_m.
     * @param cdnazione_m New value of property cdnazione_m.
     */
    public void setCdnazione_m(String cdnazione_m) {
        this.cdnazione_m = cdnazione_m;
    }
    
    /**
     * Getter for property continente.
     * @return Value of property continente.
     */
//    public Continente getContinente() {
//        return this.continente;
//    }
    
    /**
     * Setter for property continente.
     * @param continente New value of property continente.
     */
//    public void setContinente(Continente continente) {
//        this.continente = continente;
//    }
    
}
