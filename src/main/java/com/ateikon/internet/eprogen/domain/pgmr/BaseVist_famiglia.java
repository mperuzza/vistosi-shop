/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

/**
 *
 * @author emiliano
 */
public class BaseVist_famiglia extends BaseTableBean{
    
    private Mrp_arch_stato stato;

    public Mrp_arch_stato getStato() {
        return stato;
    }

    public void setStato(Mrp_arch_stato stato) {
        this.stato = stato;
    }
    
    
    
}
