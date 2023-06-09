package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Web_ord_test;
import com.ateikon.internet.eprogen.domain.pgmr.Web_ord_testExample;
import java.util.List;

public interface Web_ord_testDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int countByExample(Web_ord_testExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int deleteByExample(Web_ord_testExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int deleteByPrimaryKey(Long tkordi);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    void insert(Web_ord_test record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    void insertSelective(Web_ord_test record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    List<Web_ord_test> selectByExample(Web_ord_testExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    Web_ord_test selectByPrimaryKey(Long tkordi);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByExampleSelective(Web_ord_test record, Web_ord_testExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByExample(Web_ord_test record, Web_ord_testExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByPrimaryKeySelective(Web_ord_test record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_test
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByPrimaryKey(Web_ord_test record);

    void save(Web_ord_test record);
}