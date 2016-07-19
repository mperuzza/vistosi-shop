package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_tipi;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_tipiExample;
import java.util.List;
import java.util.Map;

public interface Vist_tipiDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int countByExample(Vist_tipiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int deleteByExample(Vist_tipiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int deleteByPrimaryKey(String cdvisttp);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    void insert(Vist_tipi record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    void insertSelective(Vist_tipi record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    List<Vist_tipi> selectByExample(Vist_tipiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    Vist_tipi selectByPrimaryKey(String cdvisttp);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int updateByExampleSelective(Vist_tipi record, Vist_tipiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int updateByExample(Vist_tipi record, Vist_tipiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int updateByPrimaryKeySelective(Vist_tipi record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    int updateByPrimaryKey(Vist_tipi record);

    List<Vist_tipi> find(Map pars);

    int countFind(Map pars);
    List<Vist_tipi> find(Map pars, String orderByClause, int pageNumber, int pagesize);

    List<Vist_tipi> getTipiAlt(Map pars);
}