package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Collezioni;
import com.ateikon.internet.eprogen.domain.pgmr.Foto_sezioni;
import com.ateikon.internet.eprogen.domain.pgmr.Foto_sezioniExample;
import com.ateikon.internet.eprogen.domain.pgmr.Foto_sezioniKey;
import java.util.List;
import java.util.Map;

public interface Foto_sezioniDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int countByExample(Foto_sezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByExample(Foto_sezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByPrimaryKey(Foto_sezioniKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insert(Foto_sezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insertSelective(Foto_sezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    List<Foto_sezioni> selectByExample(Foto_sezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    Foto_sezioni selectByPrimaryKey(Foto_sezioniKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExampleSelective(Foto_sezioni record, Foto_sezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExample(Foto_sezioni record, Foto_sezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKeySelective(Foto_sezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.foto_sezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKey(Foto_sezioni record);


    List<Collezioni> getCataloghi(Map pars);
    List<Foto_sezioni> getFotoSezioneCat(Map pars);
    List<Foto_sezioni> getFotoSezioneByCdpadre(Map pars);
    List<Foto_sezioni> getFotoSezione(Map pars);

}