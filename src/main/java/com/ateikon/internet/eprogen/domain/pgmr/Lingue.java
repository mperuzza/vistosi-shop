package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class Lingue extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.lingue.cdlingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private Integer cdlingua;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.lingue.dslingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private String dslingua;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.lingue.cdlingua
     *
     * @return the value of pgmr.lingue.cdlingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Integer getCdlingua() {
        return cdlingua;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.lingue.cdlingua
     *
     * @param cdlingua the value for pgmr.lingue.cdlingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setCdlingua(Integer cdlingua) {
        this.cdlingua = cdlingua;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.lingue.dslingua
     *
     * @return the value of pgmr.lingue.dslingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getDslingua() {
        return dslingua;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.lingue.dslingua
     *
     * @param dslingua the value for pgmr.lingue.dslingua
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setDslingua(String dslingua) {
        this.dslingua = dslingua == null ? null : dslingua.trim();
    }
}