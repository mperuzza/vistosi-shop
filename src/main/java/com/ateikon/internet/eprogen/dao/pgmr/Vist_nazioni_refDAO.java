package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_nazioni_ref;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_nazioni_refExample;
import java.util.List;

public interface Vist_nazioni_refDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int countByExample(Vist_nazioni_refExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int deleteByExample(Vist_nazioni_refExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int deleteByPrimaryKey(Long tknaziref);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    void insert(Vist_nazioni_ref record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    void insertSelective(Vist_nazioni_ref record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    List<Vist_nazioni_ref> selectByExample(Vist_nazioni_refExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    Vist_nazioni_ref selectByPrimaryKey(Long tknaziref);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int updateByExampleSelective(Vist_nazioni_ref record, Vist_nazioni_refExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int updateByExample(Vist_nazioni_ref record, Vist_nazioni_refExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int updateByPrimaryKeySelective(Vist_nazioni_ref record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    int updateByPrimaryKey(Vist_nazioni_ref record);
}