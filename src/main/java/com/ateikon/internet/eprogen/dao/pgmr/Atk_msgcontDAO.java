package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Atk_msgcont;
import com.ateikon.internet.eprogen.domain.pgmr.Atk_msgcontExample;
import com.ateikon.internet.eprogen.domain.pgmr.Atk_msgcontKey;
import java.util.List;

public interface Atk_msgcontDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int countByExample(Atk_msgcontExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int deleteByExample(Atk_msgcontExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int deleteByPrimaryKey(Atk_msgcontKey key);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    void insert(Atk_msgcont record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    void insertSelective(Atk_msgcont record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    List<Atk_msgcont> selectByExample(Atk_msgcontExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    Atk_msgcont selectByPrimaryKey(Atk_msgcontKey key);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int updateByExampleSelective(Atk_msgcont record, Atk_msgcontExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int updateByExample(Atk_msgcont record, Atk_msgcontExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int updateByPrimaryKeySelective(Atk_msgcont record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    int updateByPrimaryKey(Atk_msgcont record);
}