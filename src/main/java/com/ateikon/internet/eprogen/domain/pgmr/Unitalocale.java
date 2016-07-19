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
public class Unitalocale extends BaseTableBean{

    public Unitalocale() {
        unitalocali = new Unitalocali();
        enteuniloc  = new Enteuniloc();
        province    = new Province();
        nazioni     = new Nazioni();
        radiale     = new Radiali_comuni();
        comune      = new Comunita();
        unitalocali_geo = new Unitalocali_geo();
        description = "";
    }



    private Unitalocali unitalocali;

    private Enteuniloc enteuniloc;

    private Province province;

    private Nazioni nazioni;

    private Radiali_comuni radiale;

    private Comunita comune;

    private Unitalocali_geo unitalocali_geo;

    private String description;

    public Enteuniloc getEnteuniloc() {
        return enteuniloc;
    }

    public void setEnteuniloc(Enteuniloc enteuniloc) {
        this.enteuniloc = enteuniloc;
    }

    public Nazioni getNazioni() {
        return nazioni;
    }

    public void setNazioni(Nazioni nazioni) {
        this.nazioni = nazioni;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Unitalocali getUnitalocali() {
        return unitalocali;
    }

    public void setUnitalocali(Unitalocali unitalocali) {
        this.unitalocali = unitalocali;
    }

    public Radiali_comuni getRadiale() {
        return radiale;
    }

    public void setRadiale(Radiali_comuni radiale) {
        this.radiale = radiale;
    }

    public String getDescription() {
        description = StringUtils.trimToEmpty(comune.getDscomu()) + "|" +
                StringUtils.trimToEmpty(unitalocali.getCdunil_m()) + "|" +
                StringUtils.trimToEmpty(province.getDsprov()) + "|" +
                StringUtils.trimToEmpty(unitalocali.getCap()) + "|";
        String indiri = StringUtils.trimToNull(unitalocali.getIndiri());
        if(indiri==null) indiri = "-";
        description += indiri + "|" +
                StringUtils.trimToEmpty(nazioni.getDsnazi()) + "|" +
                StringUtils.trimToEmpty(unitalocali.getDsunil()) +
                "";

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comunita getComune() {
        return comune;
    }

    public void setComune(Comunita comune) {
        this.comune = comune;
    }

    public Unitalocali_geo getUnitalocali_geo() {
        return unitalocali_geo;
    }

    public void setUnitalocali_geo(Unitalocali_geo unitalocali_geo) {
        this.unitalocali_geo = unitalocali_geo;
    }





}
