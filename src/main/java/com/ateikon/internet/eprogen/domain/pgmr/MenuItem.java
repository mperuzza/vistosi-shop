/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.pgmr;

/**
 *
 * @author emiliano
 */
public class MenuItem {

    private Integer cdlinea;

    private String dslinea;

    private String cdcollezione;

    private String dscollezione;

    private Integer cxcoll;

    private Integer cxlinea;


    public String getCdcollezione() {
        return cdcollezione;
    }

    public void setCdcollezione(String cdcollezione) {
        this.cdcollezione = cdcollezione;
    }

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

    public Integer getCxcoll() {
        return cxcoll;
    }

    public void setCxcoll(Integer cxcoll) {
        this.cxcoll = cxcoll;
    }

    public Integer getCxlinea() {
        return cxlinea;
    }

    public void setCxlinea(Integer cxlinea) {
        this.cxlinea = cxlinea;
    }

    

}
