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
public class Web_ord_posititoBase extends BaseTableBean{

    public Web_ord_posititoBase() {
        this.web_ord_posi_note = new Web_ord_posi_note();
    }



    private  Mrp_arch_articoli articolo;

    public void setArticolo(Mrp_arch_articoli articolo) {
        this.articolo = articolo;
    }

    public Mrp_arch_articoli getArticolo() {
        return articolo;
    }


    private Web_ord_posi_note web_ord_posi_note;

    public Web_ord_posi_note getWeb_ord_posi_note() {
        return web_ord_posi_note;
    }

    public void setWeb_ord_posi_note(Web_ord_posi_note web_ord_posi_note) {
        this.web_ord_posi_note = web_ord_posi_note;
    }

    private Mrp_arch_articoli articolorif;

    public Mrp_arch_articoli getArticolorif() {
        return articolorif;
    }

    public void setArticolorif(Mrp_arch_articoli articolorif) {
        this.articolorif = articolorif;
    }
    


}
