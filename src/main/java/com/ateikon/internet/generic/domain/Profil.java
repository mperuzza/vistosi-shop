/*
 * Profil.java
 *
 * Created on 18 aprile 2005, 12.20
 */

package com.ateikon.internet.generic.domain;

import com.ateikon.internet.generic.dao.CostanteDao;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Emiliano
 */
public class Profil extends BaseTableBean{
    
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass()); 
    
    private CostanteDao costanteDao;    
    
    public void setCostanteDao(CostanteDao costanteDao) {
        this.costanteDao = costanteDao;
	}   
    
    
    /**
     * Holds value of property cdagen.
     */
    private String cdagen;

    /**
     * Holds value of property owner.
     */
    private String owner;

    /**
     * Holds value of property owner_web.
     */
    private String owner_web;

    /**
     * Holds value of property owner_gest.
     */
    private String owner_gest;

    /**
     * Getter for property cdagen.
     * @return Value of property cdagen.
     */
    public String getCdagen() {

        return this.cdagen;
    }

    /**
     * Setter for property cdagen.
     * @param cdagen New value of property cdagen.
     */
    public void setCdagen(String cdagen) {

        this.cdagen = cdagen;
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

    /**
     * Getter for property owner_web.
     * @return Value of property owner_web.
     */
    public String getOwner_web() {

        return this.owner_web;
    }

    /**
     * Setter for property owner_web.
     * @param owner_web New value of property owner_web.
     */
    public void setOwner_web(String owner_web) {

        this.owner_web = owner_web;
    }

    /**
     * Getter for property owner_gest.
     * @return Value of property owner_gest.
     */
    public String getOwner_gest() {

        return this.owner_gest;
    }

    /**
     * Setter for property owner_gest.
     * @param owner_gest New value of property owner_gest.
     */
    public void setOwner_gest(String owner_gest) {

        this.owner_gest = owner_gest;
    }

    /***************************************************************************
     *  setup
     **************************************************************************/        
     private void init(){
         //log.debug("costanti: " +this.constants);
         //this.setOwner(this.constants.getOwner());
         Map pars = new HashMap();
         //pars.put("owner", this.getOwner());
         //pars.put("cdazie", this.constants.getCdazie());
         //pars.put("table_sfx", "_comm");
         pars.put("costname", "profil.cdazie");
         this.setCdazie(this.costanteDao.getCostante(pars).getCostvalue());
         pars.put("costname", "profil.cddipa");
         this.setCddipa(this.costanteDao.getCostante(pars).getCostvalue());
         pars.put("costname", "profil.profil");
         this.setProfil(this.costanteDao.getCostante(pars).getCostvalue());
         pars.put("costname", "profil.cdagen");
         this.setCdagen(this.costanteDao.getCostante(pars).getCostvalue());
         pars.put("costname", "profil.owner_web");
         this.setOwner_web(this.costanteDao.getCostante(pars).getCostvalue());
         pars.put("costname", "profil.owner_gest");
         this.setOwner_gest(this.costanteDao.getCostante(pars).getCostvalue());
         log.debug(this);
     }    

    
}
