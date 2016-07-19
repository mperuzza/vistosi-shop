/*
 * BaseTableBean.java
 *
 * Created on 8 luglio 2004, 15.58
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author Emiliano
 */
public class BaseTableBean extends Object implements Serializable {
    
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
     * Holds value of property dtinse.
     */
    private Date dtinse;
    
    /**
     * Holds value of property dtulag.
     */
    private Date dtulag;
    
    public BaseTableBean() {
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
     * Getter for property dtinse.
     * @return Value of property dtinse.
     */
    public Date getDtinse() {
        return this.dtinse;
    }
    
    /**
     * Setter for property dtinse.
     * @param dtinse New value of property dtinse.
     */
    public void setDtinse(Date dtinse) {
        this.dtinse = dtinse;
    }
    
    /**
     * Getter for property dtulag.
     * @return Value of property dtulag.
     */
    public Date getDtulag() {
        return this.dtulag;
    }
    
    /**
     * Setter for property dtulag.
     * @param dtulag New value of property dtulag.
     */
    public void setDtulag(Date dtulag) {
        this.dtulag = dtulag;
    }

    public void setProfilo(BaseTableBean bean){
        this.setProfil(bean.getProfil());
        this.setCdazie(bean.getCdazie());
        this.setCddipa(bean.getCddipa());        
    }
    
    
    public String toString() {
        //return this.toString();
        return ToStringBuilder.reflectionToString(this,
                /*ToStringStyle.DEFAULT_STYLE);   */     
        ToStringStyle.MULTI_LINE_STYLE);        
    }
    
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }    
}
