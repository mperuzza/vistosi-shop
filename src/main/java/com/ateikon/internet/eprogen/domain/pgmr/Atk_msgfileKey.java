package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class Atk_msgfileKey extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.atk_msgfile.nomefile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    private String nomefile;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.atk_msgfile.pathfile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    private String pathfile;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.atk_msgfile.tkmsg
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    private Long tkmsg;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.atk_msgfile.nomefile
     *
     * @return the value of pgmr.atk_msgfile.nomefile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public String getNomefile() {
        return nomefile;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.atk_msgfile.nomefile
     *
     * @param nomefile the value for pgmr.atk_msgfile.nomefile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void setNomefile(String nomefile) {
        this.nomefile = nomefile == null ? null : nomefile.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.atk_msgfile.pathfile
     *
     * @return the value of pgmr.atk_msgfile.pathfile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public String getPathfile() {
        return pathfile;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.atk_msgfile.pathfile
     *
     * @param pathfile the value for pgmr.atk_msgfile.pathfile
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void setPathfile(String pathfile) {
        this.pathfile = pathfile == null ? null : pathfile.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.atk_msgfile.tkmsg
     *
     * @return the value of pgmr.atk_msgfile.tkmsg
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public Long getTkmsg() {
        return tkmsg;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.atk_msgfile.tkmsg
     *
     * @param tkmsg the value for pgmr.atk_msgfile.tkmsg
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void setTkmsg(Long tkmsg) {
        this.tkmsg = tkmsg;
    }
}