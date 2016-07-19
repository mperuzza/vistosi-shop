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
public class BaseWishlist_posi extends BaseTableBean {

    private String dsarticolo;

    private String dsfinitura;

    public String getDsarticolo() {
        return dsarticolo;
    }

    public void setDsarticolo(String dsarticolo) {
        this.dsarticolo = dsarticolo;
    }

    public String getDsfinitura() {
        return dsfinitura;
    }

    public void setDsfinitura(String dsfinitura) {
        this.dsfinitura = dsfinitura;
    }


}
