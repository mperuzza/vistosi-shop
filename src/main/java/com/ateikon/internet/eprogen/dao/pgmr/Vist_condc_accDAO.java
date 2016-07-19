package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_condc_acc;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_condc_accExample;
import java.util.List;

public interface Vist_condc_accDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int countByExample(Vist_condc_accExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int deleteByExample(Vist_condc_accExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int deleteByPrimaryKey(Long tkvist_condcacc);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    void insert(Vist_condc_acc record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    void insertSelective(Vist_condc_acc record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    List<Vist_condc_acc> selectByExample(Vist_condc_accExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    Vist_condc_acc selectByPrimaryKey(Long tkvist_condcacc);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int updateByExampleSelective(Vist_condc_acc record, Vist_condc_accExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int updateByExample(Vist_condc_acc record, Vist_condc_accExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int updateByPrimaryKeySelective(Vist_condc_acc record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_condc_acc
     *
     * @ibatorgenerated Mon Jul 22 11:01:01 CEST 2013
     */
    int updateByPrimaryKey(Vist_condc_acc record);

    Vist_condc_acc getLastCondcAcc(Long tkutente);
}