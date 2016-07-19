/*
 * ProgenCall.java
 *
 * Created on 2 luglio 2004, 10.43
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Emiliano
 */
public class AtkPargen extends BaseTableBean implements Serializable {
    
    /**
     * Holds value of property parametro.
     */
    private String parametro;
    
    /**
     * Holds value of property valore.
     */
    private String valore;
    
    public AtkPargen() {
    }
    
    /**
     * Getter for property field.
     * @return Value of property field.
     */
    public String getParametro() {
        return this.parametro;
    }
    
    /**
     * Setter for property field.
     * @param field New value of property field.
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    /**
     * Getter for property value.
     * @return Value of property value.
     */
    public String getValore() {
        return this.valore;
    }
    
    /**
     * Setter for property value.
     * @param value New value of property value.
     */
    public void setValore(String valore) {
        this.valore = valore;
    }
    
}
