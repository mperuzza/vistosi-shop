package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class Articoli_abbinatiKey extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.articoli_abbinati.cdarticolo
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private String cdarticolo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.articoli_abbinati.cdarticoloabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private String cdarticoloabb;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.articoli_abbinati.cdlistaabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private Integer cdlistaabb;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.articoli_abbinati.cdarticolo
     *
     * @return the value of pgmr.articoli_abbinati.cdarticolo
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getCdarticolo() {
        return cdarticolo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.articoli_abbinati.cdarticolo
     *
     * @param cdarticolo the value for pgmr.articoli_abbinati.cdarticolo
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setCdarticolo(String cdarticolo) {
        this.cdarticolo = cdarticolo == null ? null : cdarticolo.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.articoli_abbinati.cdarticoloabb
     *
     * @return the value of pgmr.articoli_abbinati.cdarticoloabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getCdarticoloabb() {
        return cdarticoloabb;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.articoli_abbinati.cdarticoloabb
     *
     * @param cdarticoloabb the value for pgmr.articoli_abbinati.cdarticoloabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setCdarticoloabb(String cdarticoloabb) {
        this.cdarticoloabb = cdarticoloabb == null ? null : cdarticoloabb.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.articoli_abbinati.cdlistaabb
     *
     * @return the value of pgmr.articoli_abbinati.cdlistaabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Integer getCdlistaabb() {
        return cdlistaabb;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.articoli_abbinati.cdlistaabb
     *
     * @param cdlistaabb the value for pgmr.articoli_abbinati.cdlistaabb
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setCdlistaabb(Integer cdlistaabb) {
        this.cdlistaabb = cdlistaabb;
    }
}