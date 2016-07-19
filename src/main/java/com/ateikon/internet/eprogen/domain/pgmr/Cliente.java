/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author emiliano
 */
public class Cliente extends BaseTableBean implements Serializable{

    public Cliente() {
        this.archclie = new Archclie();
        this.archenti = new Archenti();
        this.unitalocale = new Unitalocale();
        this.sedeLegale = new Unitalocale();
        this.destinazioneMerce = new Unitalocali();
    }



    private Archclie archclie;

    private Archenti archenti;

    private Unitalocale unitalocale;

    private Unitalocale sedeLegale;

    private Unitalocali destinazioneMerce;

    private List<Unitalocale> unilocs;

    public Archclie getArchclie() {
        return archclie;
    }

    public void setArchclie(Archclie archclie) {
        this.archclie = archclie;
    }

    public Archenti getArchenti() {
        return archenti;
    }

    public void setArchenti(Archenti archenti) {
        this.archenti = archenti;
    }

    public Unitalocali getDestinazioneMerce() {
        return destinazioneMerce;
    }

    public void setDestinazioneMerce(Unitalocali destinazioneMerce) {
        this.destinazioneMerce = destinazioneMerce;
    }

    public Unitalocale getSedeLegale() {
        return sedeLegale;
    }

    public void setSedeLegale(Unitalocale sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public Unitalocale getUnitalocale() {
        return unitalocale;
    }

    public void setUnitalocale(Unitalocale unitalocale) {
        this.unitalocale = unitalocale;
    }

    public List<Unitalocale> getUnilocs() {
        return unilocs;
    }

    public void setUnilocs(List<Unitalocale> unilocs) {
        this.unilocs = unilocs;
    }

    

}
