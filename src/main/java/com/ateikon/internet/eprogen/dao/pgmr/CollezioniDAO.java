package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Collezioni;
import com.ateikon.internet.eprogen.domain.pgmr.CollezioniExample;
import com.ateikon.internet.eprogen.domain.pgmr.CollezioniKey;
import java.util.List;

public interface CollezioniDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int countByExample(CollezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByExample(CollezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByPrimaryKey(CollezioniKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insert(Collezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insertSelective(Collezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    List<Collezioni> selectByExample(CollezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    Collezioni selectByPrimaryKey(CollezioniKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExampleSelective(Collezioni record, CollezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExample(Collezioni record, CollezioniExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKeySelective(Collezioni record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKey(Collezioni record);
}