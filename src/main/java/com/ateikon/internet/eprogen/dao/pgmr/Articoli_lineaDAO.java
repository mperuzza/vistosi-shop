package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Articoli_linea;
import com.ateikon.internet.eprogen.domain.pgmr.Articoli_lineaExample;
import com.ateikon.internet.eprogen.domain.pgmr.Articoli_lineaKey;
import com.ateikon.internet.eprogen.domain.pgmr.MenuItem;
import java.util.List;
import java.util.Map;

public interface Articoli_lineaDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int countByExample(Articoli_lineaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByExample(Articoli_lineaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByPrimaryKey(Articoli_lineaKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insert(Articoli_linea record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insertSelective(Articoli_linea record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    List<Articoli_linea> selectByExample(Articoli_lineaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    Articoli_linea selectByPrimaryKey(Articoli_lineaKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExampleSelective(Articoli_linea record, Articoli_lineaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExample(Articoli_linea record, Articoli_lineaExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKeySelective(Articoli_linea record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.articoli_linea
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKey(Articoli_linea record);

    List<MenuItem> getMenu(Map pars);
    Integer countArtColl(Map pars);
    Integer countArtLinea(Map pars);
}