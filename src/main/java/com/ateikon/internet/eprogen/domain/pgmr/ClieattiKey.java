package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;

public class ClieattiKey extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.clieatti.cdatti
     *
     * @ibatorgenerated Mon Dec 16 10:10:12 CET 2013
     */
    private String cdatti;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.clieatti.cdatti
     *
     * @return the value of pgmr.clieatti.cdatti
     *
     * @ibatorgenerated Mon Dec 16 10:10:12 CET 2013
     */
    public String getCdatti() {
        return cdatti;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.clieatti.cdatti
     *
     * @param cdatti the value for pgmr.clieatti.cdatti
     *
     * @ibatorgenerated Mon Dec 16 10:10:12 CET 2013
     */
    public void setCdatti(String cdatti) {
        this.cdatti = cdatti == null ? null : cdatti.trim();
    }
}