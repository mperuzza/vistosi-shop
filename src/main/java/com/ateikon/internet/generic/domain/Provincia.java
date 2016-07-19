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
public class Provincia extends BaseTableBean implements Serializable {
    
    /**
     * Holds value of property dsprov.
     */
    private String dsprov;
    
    /**
     * Holds value of property cdprov_m.
     */
    private String cdprov_m;
    
    /**
     * Holds value of property cdprov.
     */
    private String cdprov;
    
    /**
     * Holds value of property cdregi.
     */
    private String cdregi;
    
    public Provincia() {
    }
    
    /**
     * Getter for property cdvoce.
     * @return Value of property cdvoce.
     */
    public String getDsprov() {
        return this.dsprov;
    }    
    
    /**
     * Setter for property cdvoce.
     * @param cdvoce New value of property cdvoce.
     */
    public void setDsprov(String dsprov) {
        this.dsprov = dsprov;
    }
    
    /**
     * Getter for property cdutente.
     * @return Value of property cdutente.
     */
    public String getCdprov_m() {
        return this.cdprov_m;
    }
    
    /**
     * Setter for property cdutente.
     * @param cdutente New value of property cdutente.
     */
    public void setCdprov_m(String cdprov_m) {
        this.cdprov_m = cdprov_m;
    }
    
    /**
     * Getter for property cdcont.
     * @return Value of property cdcont.
     */
    public String getCdprov() {
        return this.cdprov;
    }
    
    /**
     * Setter for property cdcont.
     * @param cdcont New value of property cdcont.
     */
    public void setCdprov(String cdprov) {
        this.cdprov = cdprov;
    }
    
    /**
     * Getter for property cdregi.
     * @return Value of property cdregi.
     */
    public String getCdregi() {
        return this.cdregi;
    }
    
    /**
     * Setter for property cdregi.
     * @param cdregi New value of property cdregi.
     */
    public void setCdregi(String cdregi) {
        this.cdregi = cdregi;
    }
    
}
