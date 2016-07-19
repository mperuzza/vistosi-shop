/*
 * AtkTabella.java
 *
 * Created on 25 maggio 2005, 16.16
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.ateikon.internet.generic.domain;

import java.util.Date;


/**
 *
 * @author Emiliano
 */
public class AtkTabella extends BaseTableBean{
    
    /** Creates a new instance of AtkTabella */
    public AtkTabella() {
    }

    /**
     * Holds value of property cdtabella.
     */
    private String cdtabella;

    /**
     * Getter for property cdtabella.
     * @return Value of property cdtabella.
     */
    public String getCdtabella() {

        return this.cdtabella;
    }

    /**
     * Setter for property cdtabella.
     * @param cdtabella New value of property cdtabella.
     */
    public void setCdtabella(String cdtabella) {

        this.cdtabella = cdtabella;
    }

    /**
     * Holds value of property dsoggetto.
     */
    private String dsoggetto;

    /**
     * Getter for property dsoggetto.
     * @return Value of property dsoggetto.
     */
    public String getDsoggetto() {

        return this.dsoggetto;
    }

    /**
     * Setter for property dsoggetto.
     * @param dsoggetto New value of property dsoggetto.
     */
    public void setDsoggetto(String dsoggetto) {

        this.dsoggetto = dsoggetto;
    }

    /**
     * Holds value of property dstabella.
     */
    private String dstabella;

    /**
     * Getter for property dstabella.
     * @return Value of property dstabella.
     */
    public String getDstabella() {

        return this.dstabella;
    }

    /**
     * Setter for property dstabella.
     * @param dstabella New value of property dstabella.
     */
    public void setDstabella(String dstabella) {

        this.dstabella = dstabella;
    }

    /**
     * Holds value of property fgsync.
     */
    private String fgsync;

    /**
     * Getter for property fgsync.
     * @return Value of property fgsync.
     */
    public String getFgsync() {

        return this.fgsync;
    }

    /**
     * Setter for property fgsync.
     * @param fgsync New value of property fgsync.
     */
    public void setFgsync(String fgsync) {

        this.fgsync = fgsync;
    }

    /**
     * Holds value of property seqsync.
     */
    private Integer seqsync;

    /**
     * Getter for property seqsync.
     * @return Value of property seqsync.
     */
    public Integer getSeqsync() {

        return this.seqsync;
    }

    /**
     * Setter for property seqsync.
     * @param seqsync New value of property seqsync.
     */
    public void setSeqsync(Integer seqsync) {

        this.seqsync = seqsync;
    }

    /**
     * Holds value of property dtsync.
     */
    private Date dtsync;

    /**
     * Getter for property dtsync.
     * @return Value of property dtsync.
     */
    public Date getDtsync() {

        return this.dtsync;
    }

    /**
     * Setter for property dtsync.
     * @param dtsync New value of property dtsync.
     */
    public void setDtsync(Date dtsync) {

        this.dtsync = dtsync;
    }

    /**
     * Holds value of property dtwebsync.
     */
    private Date dtwebsync;

    /**
     * Getter for property dtwebsync.
     * @return Value of property dtwebsync.
     */
    public Date getDtwebsync() {

        return this.dtwebsync;
    }

    /**
     * Setter for property dtwebsync.
     * @param dtwebsync New value of property dtwebsync.
     */
    public void setDtwebsync(Date dtwebsync) {

        this.dtwebsync = dtwebsync;
    }
    
}
