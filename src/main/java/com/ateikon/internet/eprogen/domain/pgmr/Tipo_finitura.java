package com.ateikon.internet.eprogen.domain.pgmr;

public class Tipo_finitura extends Tipo_finituraKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.tipo_finitura.dstipofinitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private String dstipofinitura;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.tipo_finitura.dstipofinitura
     *
     * @return the value of pgmr.tipo_finitura.dstipofinitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getDstipofinitura() {
        return dstipofinitura;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.tipo_finitura.dstipofinitura
     *
     * @param dstipofinitura the value for pgmr.tipo_finitura.dstipofinitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setDstipofinitura(String dstipofinitura) {
        this.dstipofinitura = dstipofinitura == null ? null : dstipofinitura.trim();
    }
}