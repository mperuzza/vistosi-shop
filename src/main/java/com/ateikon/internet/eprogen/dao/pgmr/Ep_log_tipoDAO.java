package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Ep_log_tipo;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_log_tipoExample;
import java.util.List;

public interface Ep_log_tipoDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int countByExample(Ep_log_tipoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int deleteByExample(Ep_log_tipoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int deleteByPrimaryKey(Long tkeplog_tipo);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    void insert(Ep_log_tipo record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    void insertSelective(Ep_log_tipo record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    List<Ep_log_tipo> selectByExample(Ep_log_tipoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    Ep_log_tipo selectByPrimaryKey(Long tkeplog_tipo);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int updateByExampleSelective(Ep_log_tipo record, Ep_log_tipoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int updateByExample(Ep_log_tipo record, Ep_log_tipoExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int updateByPrimaryKeySelective(Ep_log_tipo record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_log_tipo
     *
     * @ibatorgenerated Fri Dec 10 16:57:48 CET 2010
     */
    int updateByPrimaryKey(Ep_log_tipo record);
}