package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class Ep_subutente_ulocKey extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.ep_subutente_uloc.cdunil
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    private String cdunil;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.ep_subutente_uloc.tksubutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    private Long tksubutente;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.ep_subutente_uloc.cdunil
     *
     * @return the value of pgmr.ep_subutente_uloc.cdunil
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    public String getCdunil() {
        return cdunil;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.ep_subutente_uloc.cdunil
     *
     * @param cdunil the value for pgmr.ep_subutente_uloc.cdunil
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    public void setCdunil(String cdunil) {
        this.cdunil = cdunil == null ? null : cdunil.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.ep_subutente_uloc.tksubutente
     *
     * @return the value of pgmr.ep_subutente_uloc.tksubutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    public Long getTksubutente() {
        return tksubutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.ep_subutente_uloc.tksubutente
     *
     * @param tksubutente the value for pgmr.ep_subutente_uloc.tksubutente
     *
     * @ibatorgenerated Tue Jul 05 15:28:53 CEST 2011
     */
    public void setTksubutente(Long tksubutente) {
        this.tksubutente = tksubutente;
    }
}