/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.form;

import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_posts;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_colori_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_cp_collezioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_elettrificazioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_etichette;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_mont;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_var1;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_var2;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_var3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emiliano
 */
public class SchedaArticolo {

    private Mrp_arch_articoli articolo;

    public Mrp_arch_articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(Mrp_arch_articoli articolo) {
        this.articolo = articolo;
    }

    List<Vist_var1> varianti1;

    List<Vist_var2> varianti2;

    List<Vist_var3> varianti3;

    List<Vist_finit_mont> finit_mont;

    List<Vist_finit_vetro> finit_vetro;

    List<Vist_colori_vetro> colori_vetro;

    List<Vist_elettrificazioni> elettrificazioni;

    public List<Vist_colori_vetro> getColori_vetro() {
        return colori_vetro;
    }

    public void setColori_vetro(List<Vist_colori_vetro> colori_vetro) {
        this.colori_vetro = colori_vetro;
    }

    public List<Vist_elettrificazioni> getElettrificazioni() {
        return elettrificazioni;
    }

    public void setElettrificazioni(List<Vist_elettrificazioni> elettrificazioni) {
        this.elettrificazioni = elettrificazioni;
    }

    public List<Vist_finit_mont> getFinit_mont() {
        return finit_mont;
    }

    public void setFinit_mont(List<Vist_finit_mont> finit_mont) {
        this.finit_mont = finit_mont;
    }

    public List<Vist_var1> getVarianti1() {
        return varianti1;
    }

    public void setVarianti1(List<Vist_var1> varianti1) {
        this.varianti1 = varianti1;
    }

    public List<Vist_var2> getVarianti2() {
        return varianti2;
    }

    public void setVarianti2(List<Vist_var2> varianti2) {
        this.varianti2 = varianti2;
    }

    public List<Vist_var3> getVarianti3() {
        return varianti3;
    }

    public void setVarianti3(List<Vist_var3> varianti3) {
        this.varianti3 = varianti3;
    }

    public List<Vist_finit_vetro> getFinit_vetro() {
        return finit_vetro;
    }

    public void setFinit_vetro(List<Vist_finit_vetro> finit_vetro) {
        this.finit_vetro = finit_vetro;
    }

    List<Mrp_arch_articoli> articoli;

    public List<Mrp_arch_articoli> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Mrp_arch_articoli> articoli) {
        this.articoli = articoli;
    }

    List<Vist_etichette> etichette;

    public void setEtichette(List<Vist_etichette> etichette) {
        this.etichette = etichette;
    }

    public List<Vist_etichette> getEtichette() {
        return etichette;
    }

    List<Mrp_arch_articoli> ricambi;

    public List<Mrp_arch_articoli> getRicambi() {
        return ricambi;
    }

    public void setRicambi(List<Mrp_arch_articoli> ricambi) {
        this.ricambi = ricambi;
    }

    Mrp_arch_articoli ricambio;

    public Mrp_arch_articoli getRicambio() {
        return ricambio;
    }

    public void setRicambio(Mrp_arch_articoli ricambio) {
        this.ricambio = ricambio;
    }

    
    List<Vist_vetro> vetro;

    public List<Vist_vetro> getVetro() {
        return vetro;
    }

    public void setVetro(List<Vist_vetro> vetro) {
        this.vetro = vetro;
    }

    
    LinkedList<Map<String, String>> certList = new LinkedList<Map<String, String>>();

    public LinkedList<Map<String, String>> getCertList() {
        return certList;
    }

    public void setCertList(LinkedList<Map<String, String>> certList) {
        this.certList = certList;
    }

    List<Ep_posts> techNewsList;

    public List<Ep_posts> getTechNewsList() {
        return techNewsList;
    }

    public void setTechNewsList(List<Ep_posts> techNewsList) {
        this.techNewsList = techNewsList;
    }
    
    String energyClass;

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }
    
    List<String> etichetteDatiExtra;

    public List<String> getEtichetteDatiExtra() {
        return etichetteDatiExtra;
    }

    public void setEtichetteDatiExtra(List<String> etichetteDatiExtra) {
        this.etichetteDatiExtra = etichetteDatiExtra;
    }
    
    private Vist_cp_collezioni vist_cp_collezioni;

    public Vist_cp_collezioni getVist_cp_collezioni() {
        return vist_cp_collezioni;
    }

    public void setVist_cp_collezioni(Vist_cp_collezioni vist_cp_collezioni) {
        this.vist_cp_collezioni = vist_cp_collezioni;
    }
    
    
}
