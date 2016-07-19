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
public class Unimisura extends BaseTableBean implements Serializable {
    
    /**
     * Holds value of property dsunim.
     */
    private String dsunim;
    
    /**
     * Holds value of property cdunim.
     */
    private String cdunim;
    
    /**
     * Holds value of property cdunim_m.
     */
    private String cdunim_m;
    
    /**
     * Holds value of property continente.
     */
    //private Continente continente;
    
    public Unimisura() {
    }
    
    /**
     * Getter for property cdvoce.
     * @return Value of property cdvoce.
     */
    public String getDsunim()  {

        return this.dsunim;
    }    
    
    /**
     * Setter for property cdvoce.
     * @param cdvoce New value of property cdvoce.
     */
    public void setDsunim(java.lang.String dsunim)  {

        this.dsunim = dsunim;
    }
    
    /**
     * Getter for property cdutente.
     * @return Value of property cdutente.
     */
    public String getCdunim()  {

        return this.cdunim;
    }
    
    /**
     * Setter for property cdutente.
     * @param cdutente New value of property cdutente.
     */
    public void setCdunim(java.lang.String cdunim)  {

        this.cdunim = cdunim;
    }
    
    /**
     * Getter for property cdnazione_m.
     * @return Value of property cdnazione_m.
     */
    public String getCdunim_m()  {

        return this.cdunim_m;
    }
    
    /**
     * Setter for property cdnazione_m.
     * @param cdnazione_m New value of property cdnazione_m.
     */
    public void setCdunim_m(java.lang.String cdunim_m)  {

        this.cdunim_m = cdunim_m;
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
