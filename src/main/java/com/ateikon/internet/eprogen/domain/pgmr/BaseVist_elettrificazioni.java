/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author emiliano
 */
public class BaseVist_elettrificazioni extends BaseTableBean{
    
    private Mrp_arch_stato stato;

    public Mrp_arch_stato getStato() {
        return stato;
    }

    public void setStato(Mrp_arch_stato stato) {
        this.stato = stato;
    }    
    
    private String simbAttacco;

    public String getSimbAttacco() {
        
        String prefix = StringUtils.substringBefore(simbAttacco,"\\");
        
        return prefix;
    }

    public void setSimbAttacco(String simbAttacco) {
        this.simbAttacco = simbAttacco;
    }
    
    
    
}
