/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_tipi;

/**
 *
 * @author emiliano
 */
public class DesignerProduct {

    private Vist_famiglia fam;
    private Vist_tipi tip;

    public DesignerProduct() {
        this.fam = new Vist_famiglia();
        this.tip = new Vist_tipi();
    }



    public Vist_famiglia getFam() {
        return fam;
    }

    public void setFam(Vist_famiglia fam) {
        this.fam = fam;
    }

    public Vist_tipi getTip() {
        return tip;
    }

    public void setTip(Vist_tipi tip) {
        this.tip = tip;
    }



}
