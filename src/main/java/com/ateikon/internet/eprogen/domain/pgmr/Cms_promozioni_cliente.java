package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class Cms_promozioni_cliente extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.cms_promozioni_cliente.tkproclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    private Long tkproclie;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.cms_promozioni_cliente.tkpromo
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    private Long tkpromo;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.cms_promozioni_cliente.tkclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    private String tkclie;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.cms_promozioni_cliente.cdagen
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    private String cdagen;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.cms_promozioni_cliente.tkproclie
     *
     * @return the value of pgmr.cms_promozioni_cliente.tkproclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public Long getTkproclie() {
        return tkproclie;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.cms_promozioni_cliente.tkproclie
     *
     * @param tkproclie the value for pgmr.cms_promozioni_cliente.tkproclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void setTkproclie(Long tkproclie) {
        this.tkproclie = tkproclie;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.cms_promozioni_cliente.tkpromo
     *
     * @return the value of pgmr.cms_promozioni_cliente.tkpromo
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public Long getTkpromo() {
        return tkpromo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.cms_promozioni_cliente.tkpromo
     *
     * @param tkpromo the value for pgmr.cms_promozioni_cliente.tkpromo
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void setTkpromo(Long tkpromo) {
        this.tkpromo = tkpromo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.cms_promozioni_cliente.tkclie
     *
     * @return the value of pgmr.cms_promozioni_cliente.tkclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public String getTkclie() {
        return tkclie;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.cms_promozioni_cliente.tkclie
     *
     * @param tkclie the value for pgmr.cms_promozioni_cliente.tkclie
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void setTkclie(String tkclie) {
        this.tkclie = tkclie == null ? null : tkclie.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.cms_promozioni_cliente.cdagen
     *
     * @return the value of pgmr.cms_promozioni_cliente.cdagen
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public String getCdagen() {
        return cdagen;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.cms_promozioni_cliente.cdagen
     *
     * @param cdagen the value for pgmr.cms_promozioni_cliente.cdagen
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void setCdagen(String cdagen) {
        this.cdagen = cdagen == null ? null : cdagen.trim();
    }
}