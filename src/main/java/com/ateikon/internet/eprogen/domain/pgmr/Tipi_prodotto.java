package com.ateikon.internet.eprogen.domain.pgmr;

public class Tipi_prodotto extends Tipi_prodottoKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column pgmr.tipi_prodotto.dstipoprodotto
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private String dstipoprodotto;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column pgmr.tipi_prodotto.dstipoprodotto
     *
     * @return the value of pgmr.tipi_prodotto.dstipoprodotto
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getDstipoprodotto() {
        return dstipoprodotto;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column pgmr.tipi_prodotto.dstipoprodotto
     *
     * @param dstipoprodotto the value for pgmr.tipi_prodotto.dstipoprodotto
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setDstipoprodotto(String dstipoprodotto) {
        this.dstipoprodotto = dstipoprodotto == null ? null : dstipoprodotto.trim();
    }
}