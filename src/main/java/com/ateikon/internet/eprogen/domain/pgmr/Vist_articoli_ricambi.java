package com.ateikon.internet.eprogen.domain.pgmr;

public class Vist_articoli_ricambi extends Vist_articoli_ricambiKey {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.vist_articoli_ricambi.fg_agg
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    private String fg_agg;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.vist_articoli_ricambi.imgv_filename
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    private String imgv_filename;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.vist_articoli_ricambi.numv
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    private Long numv;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.vist_articoli_ricambi.fg_agg
     *
     * @return the value of pgmr.vist_articoli_ricambi.fg_agg
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public String getFg_agg() {
        return fg_agg;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.vist_articoli_ricambi.fg_agg
     *
     * @param fg_agg the value for pgmr.vist_articoli_ricambi.fg_agg
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public void setFg_agg(String fg_agg) {
        this.fg_agg = fg_agg == null ? null : fg_agg.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.vist_articoli_ricambi.imgv_filename
     *
     * @return the value of pgmr.vist_articoli_ricambi.imgv_filename
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public String getImgv_filename() {
        return imgv_filename;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.vist_articoli_ricambi.imgv_filename
     *
     * @param imgv_filename the value for pgmr.vist_articoli_ricambi.imgv_filename
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public void setImgv_filename(String imgv_filename) {
        this.imgv_filename = imgv_filename == null ? null : imgv_filename.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.vist_articoli_ricambi.numv
     *
     * @return the value of pgmr.vist_articoli_ricambi.numv
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public Long getNumv() {
        return numv;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.vist_articoli_ricambi.numv
     *
     * @param numv the value for pgmr.vist_articoli_ricambi.numv
     *
     * @ibatorgenerated Wed Oct 27 13:04:37 CEST 2010
     */
    public void setNumv(Long numv) {
        this.numv = numv;
    }
}