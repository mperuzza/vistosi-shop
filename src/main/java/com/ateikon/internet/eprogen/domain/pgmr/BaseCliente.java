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
public class BaseCliente extends BaseTableBean{

    private Province provincia;

    private Nazioni nazione;

    public Nazioni getNazione() {
        return nazione;
    }

    public void setNazione(Nazioni nazione) {
        this.nazione = nazione;
    }

    public Province getProvincia() {
        return provincia;
    }

    public void setProvincia(Province provincia) {
        this.provincia = provincia;
    }


    


}
