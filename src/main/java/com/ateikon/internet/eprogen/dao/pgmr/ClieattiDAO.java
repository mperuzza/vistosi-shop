package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Clieatti;
import com.ateikon.internet.eprogen.domain.pgmr.ClieattiExample;
import com.ateikon.internet.eprogen.domain.pgmr.ClieattiKey;
import java.util.List;

public interface ClieattiDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int countByExample(ClieattiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int deleteByExample(ClieattiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int deleteByPrimaryKey(ClieattiKey key);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    void insert(Clieatti record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    void insertSelective(Clieatti record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    List<Clieatti> selectByExample(ClieattiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    Clieatti selectByPrimaryKey(ClieattiKey key);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByExampleSelective(Clieatti record, ClieattiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByExample(Clieatti record, ClieattiExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByPrimaryKeySelective(Clieatti record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.clieatti
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByPrimaryKey(Clieatti record);
}