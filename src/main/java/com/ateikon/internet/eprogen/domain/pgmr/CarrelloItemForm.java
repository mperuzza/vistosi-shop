/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.pgmr;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emiliano
 */
public class CarrelloItemForm {

    Mrp_arch_articoli articolo;
    Web_ord_test ord_test;
    Web_ord_positito ord_positito;
    List<Web_ord_positito> ord_posititos;

    public Mrp_arch_articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(Mrp_arch_articoli articolo) {
        this.articolo = articolo;
    }

    public List<Web_ord_positito> getOrd_posititos() {
        return ord_posititos;
    }

    public void setOrd_posititos(List<Web_ord_positito> ord_posititos) {
        this.ord_posititos = ord_posititos;
    }

    public Web_ord_test getOrd_test() {
        return ord_test;
    }

    public void setOrd_test(Web_ord_test ord_test) {
        this.ord_test = ord_test;
    }

    public Web_ord_positito getOrd_positito() {
        return ord_positito;
    }

    public void setOrd_positito(Web_ord_positito ord_positito) {
        this.ord_positito = ord_positito;
    }

    private double getNumArt(String fgpromo) {

        if (this.ord_posititos != null) {

            double numart = 0;

            for (Web_ord_positito web_ord_positito : ord_posititos) {

                if ("S".equals(fgpromo)) {
                    if (fgpromo.equals(web_ord_positito.getFgpromo())) {
                        numart += web_ord_positito.getQtatot().doubleValue();
                    }
                } else {
                    if ("WL".equals(ord_test.getCdstato()) || 
                        !"S".equals(web_ord_positito.getFgpromo())) {
                        numart += web_ord_positito.getQtatot().doubleValue();
                    }
                }
            }


            return numart;
        } else {
            return 0;
        }
    }

    public double getNumArt() {

        return getNumArt("N");
    }

    public double getNumArtOff() {

        return getNumArt("S");
    }

    private BigDecimal getTotListino(String fgpromo) {

        BigDecimal tot = new BigDecimal(BigInteger.ZERO);

        if (this.ord_posititos != null) {
            for (Web_ord_positito web_ord_positito : ord_posititos) {

                if ("S".equals(fgpromo)) {
                    if (fgpromo.equals(web_ord_positito.getFgpromo())) {
                        tot = tot.add(web_ord_positito.getImportoriga());
                    }
                } else {
                    if (!"S".equals(web_ord_positito.getFgpromo())) {
                        tot = tot.add(web_ord_positito.getImportoriga());
                    }
                }
            }
        }

        return tot;
    }

    public BigDecimal getTotListino() {

        return getTotListino("N");
    }

    public BigDecimal getTotListinoOff() {

        return getTotListino("S");
    }
    
    public BigDecimal getTotListinoCar() {
        
        BigDecimal bg1 = getTotListino("S"); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = getTotListino("N"); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 

        return bg1.add(bg2);
    }

    private BigDecimal getTotListinoNetto(String fgpromo) {

        BigDecimal tot = new BigDecimal(BigInteger.ZERO);

        if (this.ord_posititos != null) {
            for (Web_ord_positito web_ord_positito : ord_posititos) {

                if ("S".equals(fgpromo)) {
                    if ("S".equals(web_ord_positito.getFgpromo())) {
                        tot = tot.add(web_ord_positito.getImportonettoriga());
                    }
                } else {
                    if (!"S".equals(web_ord_positito.getFgpromo())) {
                        tot = tot.add(web_ord_positito.getImportonettoriga());
                    }
                }
            }
        }
        return tot;
    }

    public BigDecimal getTotListinoNetto() {

        return getTotListinoNetto("N");
    }

    public BigDecimal getTotListinoNettoOff() {

        return getTotListinoNetto("S");
    }
    
    public BigDecimal getTotListinoNettoCar() {
        
        BigDecimal bg1 = getTotListinoNetto("S"); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = getTotListinoNetto("N"); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 

        return bg1.add(bg2);
    }

    public BigDecimal getTotSconto() {

        return getTotListino().subtract(getTotListinoNetto());
    }

    public BigDecimal getTotScontoOff() {

        return getTotListinoOff().subtract(getTotListinoNettoOff());
    }
    
    public BigDecimal getTotScontoCar() {
        
        BigDecimal bg1 = getTotListinoOff().subtract(getTotListinoNettoOff()); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = getTotListino().subtract(getTotListinoNetto()); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 

        return bg1.add(bg2);
    }
    
    
    private BigDecimal getTotQta(String fgpromo) {

        BigDecimal tot = new BigDecimal(BigInteger.ZERO);

        for (Web_ord_positito web_ord_positito : ord_posititos) {

            if ("S".equals(fgpromo)) {
                if ("S".equals(web_ord_positito.getFgpromo())) {
                    tot = tot.add(web_ord_positito.getQtatot());
                }
            } else {
                if (!"S".equals(web_ord_positito.getFgpromo())) {
                    tot = tot.add(web_ord_positito.getQtatot());
                }
            }
        }

        return tot;
    }

    public BigDecimal getTotQta() {

        return getTotQta("N");
    }

    public BigDecimal getTotQtaOff() {

        return getTotQta("S");
    }
    
    public BigDecimal getTotQtaCar() {
        
        BigDecimal bg1 = getTotQta("S"); 
        bg1 = bg1.setScale(0, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = getTotQta("N"); 
        bg2 = bg2.setScale(0, BigDecimal.ROUND_HALF_UP); 


        return bg1.add(bg2);
    }
    

    private Double getTotNrpeso_n(String fgpromo) {

        Double tot = 0.0;

        for (Web_ord_positito web_ord_positito : ord_posititos) {

            if (web_ord_positito.getArticolo().getNrpeso_n() != null) {
                
                if ("S".equals(fgpromo)) {
                    if ("S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getNrpeso_n() * web_ord_positito.getQtatot().doubleValue();
                    }
                }else{
                    if (!"S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getNrpeso_n() * web_ord_positito.getQtatot().doubleValue();
                    }
                    
                }
            }

        }

        return tot;
    }

    public Double getTotNrpeso_n() {

        return getTotNrpeso_n("N");
    }

    public Double getTotNrpeso_nOff() {

        return getTotNrpeso_n("S");
    }
    
    public Double getTotNrpeso_nCar() {
        
        BigDecimal bg1 = new BigDecimal(getTotNrpeso_n("N")); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = new BigDecimal(getTotNrpeso_n("S")); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 


        return (bg1.doubleValue() + bg2.doubleValue());
    }

    private Double getTotNrpeso_l(String fgpromo) {

        Double tot = 0.0;

        for (Web_ord_positito web_ord_positito : ord_posititos) {

            if (web_ord_positito.getArticolo().getNrpeso_l() != null) {
                
                if ("S".equals(fgpromo)) {
                    if ("S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getNrpeso_l() * web_ord_positito.getQtatot().doubleValue();
                    }
                }else{
                    if (!"S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getNrpeso_l() * web_ord_positito.getQtatot().doubleValue();
                    }                    
                }
            }

        }

        return tot;
    }
    
    public Double getTotNrpeso_l() {

        return getTotNrpeso_l("N");
        
    }
    
    public Double getTotNrpeso_lOff() {

        return getTotNrpeso_l("S");
        
    }
    
        
    public Double getTotNrpeso_lCar() {
        
        BigDecimal bg1 = new BigDecimal(getTotNrpeso_l("N")); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = new BigDecimal(getTotNrpeso_l("S")); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 


        return (bg1.doubleValue() + bg2.doubleValue());
        
    }

    private Double getTotVolume(String fgpromo) {

        Double tot = 0.0;

        for (Web_ord_positito web_ord_positito : ord_posititos) {

            if (web_ord_positito.getArticolo().getVlunlt() != null) {
                
                if ("S".equals(fgpromo)) {
                    if ("S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getVlunlt() * web_ord_positito.getQtatot().doubleValue();
                    }
                }else{
                    if (!"S".equals(web_ord_positito.getFgpromo())) {
                        tot += web_ord_positito.getArticolo().getVlunlt() * web_ord_positito.getQtatot().doubleValue();
                    }
                }
            }

        }

        return tot;
    }

    public Double getTotVolume() {

        return getTotVolume("N");
    }

    public Double getTotVolumeOff() {

        return getTotVolume("S");
    }
    
    public Double getTotVolumeCar() {
        
        BigDecimal bg1 = new BigDecimal(getTotVolume("N")); 
        bg1 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP); 
        
        BigDecimal bg2 = new BigDecimal(getTotVolume("S")); 
        bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP); 


        return (bg1.doubleValue() + bg2.doubleValue());
    }

    public Double getDisp() {

        if (getArticolo() != null && getArticolo().getMrp_file_giacenza() != null) {

            return Math.floor(getArticolo().getMrp_file_giacenza().getQtadisp());
        } else {
            return 0.0;
        }
    }
    
    
    
//    public String getDtprdisp(){
//
//        if(getArticolo()!=null && getArticolo().getMrp_file_giacenza()!=null){
//
//            Calendar cal = GregorianCalendar.getInstance(Locale.ITALIAN);
//
//
//
//        }
//
//        return null;
//
//    }
    private Boolean condcAccepted = false;

    public Boolean getCondcAccepted() {
        return condcAccepted;
    }

    public void setCondcAccepted(Boolean condcAccepted) {
        this.condcAccepted = condcAccepted;
    }
    
    private Map condc;

    public Map getCondc() {
        return condc;
    }

    public void setCondc(Map condc) {
        this.condc = condc;
    }
    
    

    
}
