package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoliExample;
import java.util.List;

public interface Vist_filtro_articoliDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int countByExample(Vist_filtro_articoliExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int deleteByExample(Vist_filtro_articoliExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int deleteByPrimaryKey(Long tkartfil);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    void insert(Vist_filtro_articoli record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    void insertSelective(Vist_filtro_articoli record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    List<Vist_filtro_articoli> selectByExample(Vist_filtro_articoliExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    Vist_filtro_articoli selectByPrimaryKey(Long tkartfil);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int updateByExampleSelective(Vist_filtro_articoli record, Vist_filtro_articoliExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int updateByExample(Vist_filtro_articoli record, Vist_filtro_articoliExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int updateByPrimaryKeySelective(Vist_filtro_articoli record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    int updateByPrimaryKey(Vist_filtro_articoli record);

    List<String> getCdclas_aByTkclie(Archclie clie);

    List<Vist_filtro_articoli> getCdclas_aByCdlist(String cdlist);
}