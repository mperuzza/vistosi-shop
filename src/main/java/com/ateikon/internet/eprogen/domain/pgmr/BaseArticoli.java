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
public class BaseArticoli extends BaseTableBean{

    private Integer wsart;

    public Integer getWsart() {
        return wsart;
    }

    public void setWsart(Integer wsart) {
        this.wsart = wsart;
    }

    private Integer cdlinea;

    private String dscollezione;

    private String dslinea;

    public Integer getCdlinea() {
        return cdlinea;
    }

    public void setCdlinea(Integer cdlinea) {
        this.cdlinea = cdlinea;
    }

    public String getDscollezione() {
        return dscollezione;
    }

    public void setDscollezione(String dscollezione) {
        this.dscollezione = dscollezione;
    }

    public String getDslinea() {
        return dslinea;
    }

    public void setDslinea(String dslinea) {
        this.dslinea = dslinea;
    }

    
}
