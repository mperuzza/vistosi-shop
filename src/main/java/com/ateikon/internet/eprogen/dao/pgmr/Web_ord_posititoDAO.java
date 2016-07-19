package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Web_ord_positito;
import com.ateikon.internet.eprogen.domain.pgmr.Web_ord_posititoExample;
import java.util.List;

public interface Web_ord_posititoDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int countByExample(Web_ord_posititoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int deleteByExample(Web_ord_posititoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int deleteByPrimaryKey(Long tkposi);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    void insert(Web_ord_positito record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    void insertSelective(Web_ord_positito record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    List<Web_ord_positito> selectByExample(Web_ord_posititoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    Web_ord_positito selectByPrimaryKey(Long tkposi);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByExampleSelective(Web_ord_positito record, Web_ord_posititoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByExample(Web_ord_positito record, Web_ord_posititoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByPrimaryKeySelective(Web_ord_positito record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.web_ord_positito
     *
     * @ibatorgenerated Thu Jul 09 18:30:27 CEST 2009
     */
    int updateByPrimaryKey(Web_ord_positito record);
}